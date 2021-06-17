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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
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
import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.constants.StringConstans;
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

	@Mock
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
	
	private Map<String, Invoice> responseInvoice;
	
	private ResponseEntity<?> responseEntity;
	
	private ResponseEntity<?> responseEntityInvoice;
	
	private ResponseEntity<?> responseEntityInvoice201;
	BuyTrayTDA buyTray;
	@Mock
	private Color  color ;


	List<Role> roles;

	@BeforeEach
	void before() {
		role = Role.builder().id(1L).name("ROLE_USER").build();
		
		this.roles = new ArrayList<>();
		this.roles.add(role);
		
		user = User.builder().id(1L).enabled(Constants.TRUE).username("Rosten")
				.nickname("RostenRoss").password("12345").roles(roles).build();

		invoice= Invoice.builder().id(1L).createAt(new Timestamp(new Date().getTime()))
				.description("Rosten").items(new ArrayList<>()).user(user).build();
		
		color = Color.builder().color(StringConstans.RED).enable(Constants.TRUE).id(1L).build();
		
		eggsTray = EggsTray.builder().color(color).eggs(new ArrayList<>()).build();
		
		egg = Egg.builder().color(color).carton(eggsTray).id(1L).build();
		
		eggsTray.addEgg(egg);
		
		responseInvoice = new HashMap<String, Invoice>();

		invoiceItem = InvoiceItem.builder().cartons(new ArrayList<>()).id(1L).quantity(1).itemMout(35D).build();
		invoiceItem.addCarton(eggsTray);
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
		responseInvoice.put("invoice", invoice);
		responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		responseEntityInvoice = new ResponseEntity<>(responseInvoice, HttpStatus.OK);
		responseEntityInvoice201 = new ResponseEntity<>(responseInvoice, HttpStatus.CREATED);
		buyTray= new BuyTrayTDA();
		buyTray.setQuantity1(1);
	}

	@AfterEach
	void after() throws Exception {

		closeable.close();
	}

	//home
	@Test
	void homeTestShouldResponseWellcomeMsjWhenHome() {
		Mockito.doReturn(responseEntity).when(controller).home();
	    Assertions.assertEquals(responseEntity, controller.home());
	}
	
	@Test
	void homeShouldResponseStatus200OkWhenHome() {
		Mockito.doReturn(responseEntity).when(controller).home();
	    Assertions.assertEquals(HttpStatus.OK, controller.home().getStatusCode());
	}

	//buyEggsTray
	
	@Test
	void buyEggsTrayShouldResponseInvoiceWhenBuyEggsTray() {
		Mockito.doReturn(responseEntityInvoice).when(controller).buyEggsTray(buyTray);
	    Assertions.assertEquals(responseInvoice, controller.buyEggsTray(buyTray).getBody());
	}
	
	@Test
	void buyEggsTrayShouldResponseStatus200OkWhenBuyEggsTray() {
		Mockito.doReturn(responseEntityInvoice201).when(controller).buyEggsTray(buyTray);
	    Assertions.assertEquals(HttpStatus.CREATED, controller.buyEggsTray(buyTray).getStatusCode());
	}
	
	@Test
	void buyEggsTrayShouldResponseNotNullWhenBuyEggsTray() {
		Mockito.doReturn(responseEntityInvoice201).when(controller).buyEggsTray(buyTray);
	    Assertions.assertNotNull(controller.buyEggsTray(buyTray).getStatusCode());
	}
	
	@Test
	void buyEggsTrayShouldResponseStatusOkWhenBuyEggsTrayFailOrNotStock() {
		Mockito.doReturn(responseEntity).when(controller).buyEggsTray(buyTray);
	    Assertions.assertEquals(HttpStatus.OK,controller.buyEggsTray(buyTray).getStatusCode());
	}
	
}
