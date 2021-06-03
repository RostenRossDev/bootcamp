package com.globant.bootcamp.EggsShopping.models.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.globant.bootcamp.EggsShopping.models.Repository.IUserDao;
import com.globant.bootcamp.EggsShopping.models.entity.Role;
import com.globant.bootcamp.EggsShopping.models.entity.User;

import javassist.NotFoundException;

class UserServiceTest {
	@Mock
	private IUserDao repository;

	@InjectMocks
	private UserService service;
	
    private AutoCloseable closeable;
    
	private Role role;
	
	private User user;
	
	private User updateuser;
	
	private Optional<User> userOp;
	
	private org.springframework.security.core.userdetails.User userDetail;

	@BeforeEach
	void setUp() throws Exception {
		closeable =  MockitoAnnotations.openMocks(this);

		role= new Role();
		role.setId(1l);
		role.setName("ROLE_ADMIN");
		
		user = new User();
		user.setEnabled(true);
		user.setId(1L);
		user.setNickname("RostenRoss");
		user.setUsername("Nestor");
		user.setPassword("12345");
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		user.setRoles(roles);
		
		updateuser = new User();
		updateuser.setEnabled(true);
		updateuser.setId(1L);
		updateuser.setNickname("RostenRoss");
		updateuser.setUsername("Nestor Costantini");
		updateuser.setPassword("12345");
		roles.add(role);
		updateuser.setRoles(roles);
		
		userOp = Optional.of(user);
		userOp.orElse(null);
		
		List<GrantedAuthority> authorities= user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		
		userDetail = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);

	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void loadUserByUsernameTest() {
		
		given(repository.findByUsername("Nestor")).willReturn(user);
				
		UserDetails userTest = service.loadUserByUsername("Nestor");
				
	    assertEquals(userDetail, userTest);
	    
	}

	
	@Test
	void findByUsernameTest() {
		
		given(repository.findByUsername("Nestor")).willReturn(user);
				
		User userTest = service.findByUsername("Nestor");
				
	    assertEquals(user, userTest);
	    
	}
	
	@Test
	void findByIdTest() {
		
		given(repository.findById(1L)).willReturn(userOp);
				
		User userTest = service.findById(1L);
				
	    assertEquals(user, userTest);
	    
	}
	
	@Test
	void createUserTest() {
		
		given(repository.save(user)).willReturn(user);
				
		User userTest = service.createUser(user);
				
	    assertEquals(user, userTest);
	    
	}
	
	@Test
	void updateTest() {
		
		given(repository.save(updateuser)).willReturn(updateuser);
				
		User userTest = service.update(updateuser);
				
	    assertEquals(updateuser, userTest);
	    
	}

	@Test
	void allUsersTest() {
		
		given(repository.findAll()).willReturn(List.of(user, updateuser));
				
		List<User> usersTest = service.allUsers();
				
	    assertEquals(List.of(user, updateuser), usersTest);
	    
	}
	
	@Test
	void findByNickNameTest() {
		
		given(repository.findByNickname("RostenRoss")).willReturn(user);
				
		User userTest = service.findByNickName("RostenRoss");
				
	    assertEquals(user, userTest);
	    
	}
}
