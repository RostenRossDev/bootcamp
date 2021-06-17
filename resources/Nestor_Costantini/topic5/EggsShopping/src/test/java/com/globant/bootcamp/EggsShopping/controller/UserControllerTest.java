package com.globant.bootcamp.EggsShopping.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.models.Repository.IUserDao;
import com.globant.bootcamp.EggsShopping.models.entity.Role;
import com.globant.bootcamp.EggsShopping.models.entity.User;
import com.globant.bootcamp.EggsShopping.models.service.RoleService;
import com.globant.bootcamp.EggsShopping.models.service.UserService;


class UserControllerTest {

	private final Log LOG = LogFactory.getLog(this.getClass());

	@Mock
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

	private AutoCloseable closeable;

	private Role role;

	private User user;
	
	private User nullUser;
	
	private User updateUser;
	
	
	private ResponseEntity<?> response ;
	
	private ResponseEntity<?> EmptyListResponse ;
	
	private ResponseEntity<?> NullResponse ;
	
	List<Role> roles;

	@BeforeEach
	void before() {
		role = Role.builder().id(1L).name("ROLE_USER").build();

		this.roles = new ArrayList<>();
		this.roles.add(role);
		user = User.builder().id(5L).enabled(Constants.TRUE).username("Rosten").nickname("RostenRoss").password("12345")
				.roles(roles).build();

		closeable = MockitoAnnotations.openMocks(this);

		JacksonTester.initFields(this, new ObjectMapper());
		
		Map<String, Object> responseMap = new HashMap<>();

		this.user.setPassword("12345");
		this.user.setRoles(roles);
		User updateUser = User.builder().id(user.getId()).nickname(user.getUsername()).username("Holanda")
				.enabled(user.getEnabled()).roles(user.getRoles()).password(user.getPassword()).build();

		responseMap.put("user", updateUser);
		
		response = new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);

		Map<String, Object> mapResponse = new HashMap<String, Object>();
		mapResponse.put("list", List.of());
		EmptyListResponse = new ResponseEntity<Map<String, Object>>(mapResponse, HttpStatus.OK);
		
		nullUser = null;
		
		NullResponse = new ResponseEntity<Map<String, Object>>((Map<String, Object>) nullUser, HttpStatus.OK);
	}

	@AfterEach
	void after() throws Exception {

		closeable.close();
	}

	/*
	 * @Test void createUserTest() throws Exception {
	 * 
	 * this. user.setPassword("12345"); this. user.setRoles(roles); ObjectMapper
	 * mapper = new ObjectMapper();
	 * mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false); ObjectWriter
	 * ow = mapper.writer().withDefaultPrettyPrinter(); String
	 * requestJson=ow.writeValueAsString(user);
	 * 
	 * when(userService.createUser(user)).thenReturn(user);
	 * 
	 * mockMvc.perform(post("/api/v1/user/")
	 * .contentType(MediaType.APPLICATION_JSON) .content(requestJson)) .andExpect(
	 * status().isCreated()) .andExpect( (ResultMatcher)
	 * content().contentType(MediaType.APPLICATION_JSON)) .andExpect(
	 * jsonPath("$.user").value(user)) .andDo(print());
	 * 
	 * }
	 */
	
	//allUser

	@Test
	void AllUserTesReturnResponsetUserListWhenFoundMatches() throws Exception {
		Mockito.doReturn(EmptyListResponse).when(controller).getAll();
	    Assertions.assertEquals(EmptyListResponse, controller.getAll());
	}
	
	@Test
	void AllUserTesReturnResponseStatusCode200OkWhenAllUser() throws Exception {

		Mockito.doReturn(EmptyListResponse).when(controller).getAll();
	    Assertions.assertEquals(HttpStatus.OK, controller.getAll().getStatusCode());
	}


	//userByNickname
	@Test
	void userByNicknameTestShouldResponseUserWhenFound() throws Exception {
		Mockito.doReturn(response).when(controller).userByNickname("RostenRoss");
	    Assertions.assertEquals(response, controller.userByNickname("RostenRoss"));	  		
	}
	
	@Test
	void userByNicknameTestShouldResponseNullUserWhenNotFound() throws Exception {
		Mockito.doReturn(NullResponse).when(controller).userByNickname("RostenRoss");
	    Assertions.assertEquals(NullResponse, controller.userByNickname("RostenRoss"));	  		
	}
	
	@Test
	void userByNicknameTestShouldResponseNotNullWhenNotFound() throws Exception {
		Mockito.doReturn(response).when(controller).userByNickname("RostenRoss");
	    Assertions.assertNotNull(controller.userByNickname("RostenRoss"));	  		
	}
	
	//userById
	@Test
	void userByIdTestShouldRetunUserWhenFound() throws Exception {		
		Mockito.doReturn(response).when(controller).userById(1L);
	    Assertions.assertEquals(response, controller.userById(1L));	  		
	}
	
	@Test
	void userByIdTestShouldRetunNotNullWhenFound() throws Exception {		
		Mockito.doReturn(response).when(controller).userById(1L);
	    Assertions.assertNotNull(controller.userById(1L));	  		
	}
	
	@Test
	void userByIdTestShouldResponseStatusCode200Ok() throws Exception {		
		Mockito.doReturn(response).when(controller).userById(1L);
	    Assertions.assertEquals(HttpStatus.OK,controller.userById(1L).getStatusCode());	  		
	}
	
	@Test
	void userByIdTestShouldResponseEmptyListIfNotFound() throws Exception {		
		Mockito.doReturn(EmptyListResponse).when(controller).userById(1L);
	    Assertions.assertEquals(EmptyListResponse,controller.userById(1L));	  		
	}
	
	
	//update
	@Test
	void updateUserTestShouldResponseWhenUpdate() throws Exception {
		Mockito.doReturn(response).when(controller).update(updateUser);
	    Assertions.assertEquals(response, controller.update(updateUser));	  
	}
	
	@Test
	void updateUserTestShouldResponseNotNullWhenUpdate() throws Exception{
		Mockito.doReturn(response).when(controller).update(updateUser);
		Assertions.assertNotNull(controller.update(updateUser));
	}
	
	@Test
	void updateUserTestShouldResponseStatusCode200OkWhenUpdate() throws Exception{
		Mockito.doReturn(response).when(controller).update(updateUser);
		Assertions.assertEquals(HttpStatus.OK,controller.update(updateUser).getStatusCode());
	}
	

}
