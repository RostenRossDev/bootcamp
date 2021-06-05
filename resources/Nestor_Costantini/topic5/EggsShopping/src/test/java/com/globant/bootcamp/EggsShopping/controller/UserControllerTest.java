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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.models.Repository.IUserDao;
import com.globant.bootcamp.EggsShopping.models.entity.Role;
import com.globant.bootcamp.EggsShopping.models.entity.User;
import com.globant.bootcamp.EggsShopping.models.service.RoleService;
import com.globant.bootcamp.EggsShopping.models.service.UserService;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;

import springfox.documentation.builders.RequestParameterBuilderValidator;
import springfox.documentation.spring.web.json.Json;



class UserControllerTest {
	
	private final Log LOG  = LogFactory.getLog(this.getClass());
	
	@InjectMocks
	private UserController controller;

	@Mock
	private UserService userService = new UserService();
	
	@Mock
	private RoleService roleService = new RoleService();

	@Mock
	private IUserDao userRepository;
	
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	
	@Mock
	private SecurityContextHolder securityContextHolder;
	
	private MockMvc mockMvc;
	
    private AutoCloseable closeable;

    private Role role;
    
    private User user;
    
    
    List<Role> roles;
    
	@BeforeEach
	void before() {
		role = Role.builder().id(1L).name("ROLE_USER").build();
		
		this.roles = new ArrayList<>();
		this.roles.add(role);
		user= User.builder().id(5L).enabled(Constants.TRUE).username("Rosten").nickname("RostenRoss")
				.password("12345").roles(roles).build();
		
		closeable =  MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@AfterEach
	void after() throws Exception{
		
		closeable.close();
	}

	/*@Test
	void createUserTest() throws Exception {
  
        this. user.setPassword("12345");
		this. user.setRoles(roles);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(user);
        
        when(userService.createUser(user)).thenReturn(user);

        mockMvc.perform(post("/api/v1/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
        		.andExpect( status().isCreated())
        		.andExpect( (ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        		.andExpect( jsonPath("$.user").value(user))
        .andDo(print());

  	}*/
	
	@Test
	void AllUserTest() throws Exception {

        when(userService.allUsers()).thenReturn(Collections.singletonList(user));
        mockMvc.perform(get("/api/v1/user/all")).andDo(print());
	}
	
	@Test
	void userByNicknameTest() throws Exception{
		
		 when(userService.findByNickName(user.getNickname())).thenReturn(user);
	     mockMvc.perform(get("/api/v1/user/nickname:RostenRoss")).andDo(print());
	}
	
	@Test
	void userByIdTest() throws Exception{
		
		 when(userService.findById(user.getId())).thenReturn(user);
	     mockMvc.perform(get("/api/v1/user/id:1")).andDo(print());
	}
	//@Test
	void updateUserTest() throws Exception {
  
        this.user.setPassword("12345");
		this.user.setRoles(roles);
		User updateUser = User.builder().id(user.getId()).nickname(user.getUsername()).username("Holanda").enabled(user.getEnabled())
				.roles(user.getRoles()).password(user.getPassword()).build();
		
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(updateUser);
        
        when(userService.createUser(user)).thenReturn(user);

        mockMvc.perform(put("/api/v1/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
        		.andExpect( status().isOk())
        		.andExpect( (ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        		.andExpect( jsonPath("$.userId").value(user.getId()))
        		.andExpect( jsonPath("$.username").value(user.getUsername()))
        		.andExpect( jsonPath("$.nickname").value(user.getNickname()))
        		.andExpect( jsonPath("$.roles").value(user.getRoles()))
        .andDo(print());

  	}

}
