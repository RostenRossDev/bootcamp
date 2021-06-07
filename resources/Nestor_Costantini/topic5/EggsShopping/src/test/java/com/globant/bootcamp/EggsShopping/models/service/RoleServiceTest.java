package com.globant.bootcamp.EggsShopping.models.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.globant.bootcamp.EggsShopping.models.Repository.IRoleDao;
import com.globant.bootcamp.EggsShopping.models.entity.Color;
import com.globant.bootcamp.EggsShopping.models.entity.Egg;
import com.globant.bootcamp.EggsShopping.models.entity.Role;

class RoleServiceTest {
	
	private final Log LOG = LogFactory.getLog(this.getClass());	

	@Mock
	private IRoleDao repository;

	@InjectMocks
	private RoleService service;
	
    private AutoCloseable closeable;
    
	private Role role;
	
	Optional<Role> roleOp;
	
	@BeforeEach
	void setUp() throws Exception {
		closeable =  MockitoAnnotations.openMocks(this);
		
		role = Role.builder().id(1L).name("ROLE_USER").build();
		roleOp = Optional.of(role);
		roleOp.orElse(null);
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void findOneTestShouldReturnRoleWhenRepositoryContainsMatches() {
		
		given(repository.findById(1L)).willReturn(roleOp);
		
		Role roleTest = service.findOne(1L);
			    
	    assertEquals(role, roleTest);
	}
	
	@Test
	void findOneTestShouldReturnNullWhenRepositoryNotContainsMatches() {
		
		roleOp =Optional.ofNullable(null);

		given(repository.findById(1L)).willReturn(roleOp);
		
		Role roleTest = service.findOne(1L);
		
	    assertNull(roleTest);   
	}
	
	@Test
	void createRoleTestShouldThrowPersistenceExceptionWhenSaveNewPriceRepositoryFail()  throws PersistenceException {
				
		given(repository.save(role)).willThrow(new PersistenceException("The expected message"));			    
	}
	
	@Test
	void createRoleTestShouldReturnRoleWhenRepositoryPersist() throws PersistenceException {
		
		given(repository.save(role)).willReturn(role);
		
		Role roleTest = service.createRole(role);
				
	    assertEquals(role, roleTest);
    
	}

}
