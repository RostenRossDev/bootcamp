package com.globant.bootcamp.EggsShopping.models.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.globant.bootcamp.EggsShopping.models.Repository.IRoleDao;
import com.globant.bootcamp.EggsShopping.models.entity.Role;

class RoleServiceTest {
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
		
		role = new Role();
		role.setId(1L);
		role.setName("ROLE_USER");
		
		roleOp = Optional.of(role);
		roleOp.orElse(null);
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void findOneTest() {
		
		given(repository.findById(1L)).willReturn(roleOp);
		
		Role roleTest = service.findOne(1L);
			    
	    assertEquals(role, roleTest);

	}

}
