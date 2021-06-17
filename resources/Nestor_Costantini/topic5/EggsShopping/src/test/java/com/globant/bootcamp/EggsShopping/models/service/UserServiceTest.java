package com.globant.bootcamp.EggsShopping.models.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.globant.bootcamp.EggsShopping.models.Repository.IUserDao;
import com.globant.bootcamp.EggsShopping.models.entity.Role;
import com.globant.bootcamp.EggsShopping.models.entity.User;

import javassist.NotFoundException;

class UserServiceTest {
	private final Log LOG = LogFactory.getLog(this.getClass());	

	@Mock
	private IUserDao repository;

	@InjectMocks
	private UserService service;
	
    private AutoCloseable closeable;
    
	private Role role;
	
	private User user;
	
	private User updateuser;
	
	private User nullUser;
	
	private Optional<User> userOp;
	
	private org.springframework.security.core.userdetails.User userDetail;

	@BeforeEach
	void setUp() throws Exception {
		closeable =  MockitoAnnotations.openMocks(this);

		nullUser = null;
		
		role= Role.builder().id(1L).name("ROLE_ADMIN").build();
		
		user = User.builder().enabled(true).id(1L).nickname("RostenRoss")
				.username("Nestor").password("12345").build();
		
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		user.setRoles(roles);
		
		updateuser = User.builder().enabled(Boolean.TRUE).id(1L).nickname("RostenRoss")
				.username("Nestor Costantini").password("12345").build();
		
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
	void loadUserByUsernameTestShuoldReturnUserWhenRepositoryContainsMatches() {
		
		given(repository.findByUsername("Nestor")).willReturn(user);
				
		UserDetails userTest = service.loadUserByUsername("Nestor");
				
	    assertEquals(userDetail, userTest);
	    
	}
	
	@Test
	void loadUserByUsernameTestShuoldReturnThrowUsernameNotFoundException() {
	
		User nullUser = null;	
		
		given(repository.findByUsername("NestorD")).willReturn(nullUser); 
	        
		UsernameNotFoundException usernameNotFoundException =  assertThrows(UsernameNotFoundException.class, () -> {
	    	   service.loadUserByUsername("NestorD");
	       		});
	       String expectedMessage = "User NestorD don`t exist!";
	       String actualMessage = usernameNotFoundException.getMessage();

	       assertTrue(actualMessage.contains(expectedMessage));
	}


	
	@Test
	void findByUsernameTestShouldReturnUserWhenRepositoryContainsMatches() {
		
		given(repository.findByUsername("Nestor")).willReturn(user);
				
		User userTest = service.findByUsername("Nestor");
				
	    assertEquals(user, userTest);
	    
	}
	
	@Test
	void findByUsernameTestShouldReturNUllWhenRepositoryNotContainsMatches() {
		
		User nullUser = null;
		given(repository.findByUsername("Nestor")).willReturn(nullUser);
				
		User userTest = service.findByUsername("Nestor");
				
	    assertEquals(nullUser, userTest);
	    
	}
	
	
	@Test
	void findByIdTestShouldReturnUserWhenRepositoryContainMatches() {
		
		given(repository.findById(1L)).willReturn(userOp);
				
		User userTest = service.findById(1L);
				
	    assertEquals(user, userTest);
	    
	}
	
	@Test
	void findByIdTestShouldReturnNullWhenRepositoryNotContainMatches() {
		
		Optional<User> nullOpUser =Optional.ofNullable(null);
		
		given(repository.findById(1L)).willReturn(nullOpUser);
				
		User userTest = service.findById(1L);
				
	    assertEquals(nullUser, userTest);
	    
	}
	
	@Test
	void createUserTestShouldReturnUserWhenRepositoryPersist() {
		
		given(repository.save(user)).willReturn(user);
				
		User userTest = service.createUser(user);
				
	    assertEquals(user, userTest);
	    
	}
	@Test
	void createUserTestShouldReturnNullUserWhenRepositoryFailPersist() {
		
		given(repository.save(user)).willReturn(nullUser);
				
		User userTest = service.createUser(user);
				
	    assertEquals(nullUser, userTest);
	    
	}
	
	@Test
	void createUserTestShouldUserWhenRepositoryUpdate() {
		
		given(repository.save(user)).willReturn(user);
				
		User userTest = service.createUser(user);
				
	    assertEquals(user, userTest);
	    
	}
	
	@Test
	void createUserTestShouldUserWhenRepositoryFailUpdate() throws EntityNotFoundException{
		
		given(repository.save(user)).willReturn(user).willThrow(new EntityNotFoundException("The expected message"));
				
		//User userTest = service.createUser(user);
				
	    //assertEquals(user, userTest);
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
