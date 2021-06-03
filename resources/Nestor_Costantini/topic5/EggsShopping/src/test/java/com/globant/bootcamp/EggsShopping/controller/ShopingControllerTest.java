package com.globant.bootcamp.EggsShopping.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.globant.bootcamp.EggsShopping.models.Repository.IUserDao;
import com.globant.bootcamp.EggsShopping.models.entity.Color;
import com.globant.bootcamp.EggsShopping.models.entity.Egg;
import com.globant.bootcamp.EggsShopping.models.entity.EggsTray;
import com.globant.bootcamp.EggsShopping.models.entity.Invoice;
import com.globant.bootcamp.EggsShopping.models.entity.InvoiceItem;
import com.globant.bootcamp.EggsShopping.models.entity.Role;
import com.globant.bootcamp.EggsShopping.models.entity.User;
import com.globant.bootcamp.EggsShopping.models.entity.tda.BuyTrayTDA;
import com.globant.bootcamp.EggsShopping.models.service.ColorService;
import com.globant.bootcamp.EggsShopping.models.service.EggsTrayService;
import com.globant.bootcamp.EggsShopping.models.service.InvoiceService;
import com.globant.bootcamp.EggsShopping.models.service.RoleService;
import com.globant.bootcamp.EggsShopping.models.service.UserService;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;

import springfox.documentation.builders.RequestParameterBuilderValidator;
import springfox.documentation.spring.web.json.Json;

class ShopingControllerTest {

	private final Log LOG = LogFactory.getLog(this.getClass());

	@InjectMocks
	private ShopingController controller;

	@Mock
	private UserService userService = new UserService();

	@Mock
	private RoleService roleService = new RoleService();

	@Mock
	private InvoiceService invoiceService;

	@Mock
	private EggsTrayService eggTrayService;

	@Mock
	private ColorService colorService;

	private MockMvc mockMvc;

	private AutoCloseable closeable;

	private Role role;

	private User user;
	
	private Invoice	invoice;

	private InvoiceItem	invoiceItem;
	
	private EggsTray eggsTray;
	
	private Egg egg;

	private Map<String, String> response;
	
	@Mock
	private Color  color ;


	List<Role> roles;

	@BeforeEach
	void before() {
		role = new Role();
		user = new User();
		this.role.setId(1L);
		this.role.setName("ROLE_USER");
		this.roles = new ArrayList<>();
		this.roles.add(role);
		this.user.setId(1L);
		this.user.setEnabled(true);
		this.user.setUsername("Rosten");
		this.user.setNickname("RostenRoss");
		this.user.setPassword("12345");
		this.user.setRoles(roles);
		this.user.setId(1L);
		invoice= new Invoice();
		this.invoice.setId(1L);
		this.invoice.setCreateAt(new Timestamp(new Date().getTime()));
		this.invoice.setDescription("Rosten");
		this.invoice.setId(1L);
		this.invoice.setItems(new ArrayList<>());
		this.invoice.setUser(user);
		color = new Color();
		color.setColor("RED");
		color.setEnable(true);
		color.setId(1L);
		egg = new Egg();
		egg.setColor(color);
		eggsTray = new EggsTray();
		eggsTray.setColor(color);
		eggsTray.setEggs(new ArrayList<>());
		egg.setCarton(eggsTray);
		egg.setId(1L);
		eggsTray.addEgg(egg);

		invoiceItem = new InvoiceItem();
		invoiceItem.setCartons(new ArrayList<>());
		invoiceItem.addCarton(eggsTray);
		invoiceItem.setId(1L);
		invoiceItem.setQuantity(1);
		invoiceItem.setItemMout(35D);	
		invoice.addIteminvoice(invoiceItem);
		
		closeable = MockitoAnnotations.openMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		String hello = "Welcome to Humpty Dumpty Egg's Shop";
		String msj = "Please SingUp into shop eggs.";
		String url = "localhost:8080/api/v1/user/register";
		response = new HashMap<>();
		response.put("weolcomeMsj", hello);
		response.put("message", msj);
		response.put("urlRegister", url);
	}

	@AfterEach
	void after() throws Exception {

		closeable.close();
	}


	@Test
	void homeTest() throws Exception {
		 this. user.setPassword("12345");
			this. user.setRoles(roles);
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	        String requestJson=ow.writeValueAsString(user);
	        
        mockMvc.perform(get("/api/v1/eggsShoping/home")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
        		.andExpect( status().isOk())
        .andDo(print());
	}

	
	/*@Test
	void buyEggsTrayTest() throws Exception {
		
		BuyTrayTDA trayDta = new BuyTrayTDA();
		trayDta.setQuantity1(1);
	
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(trayDta);
        
		Invoice invoice = new Invoice();
		when(invoiceService.saveInvoice(invoice)).thenReturn(invoice);
		mockMvc.perform(post("/api/v1/eggsShoping/BuyEggTray")
				 .contentType(MediaType.APPLICATION_JSON)
	             .content(requestJson)
		).andDo(print());
	}
	*/
}
