package com.globant.bootcamp.EggsShopping.models.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoleTest {

	@Test
	void test() {
		
		Role roleAdmin = new Role();
		roleAdmin.setId(1L);
		roleAdmin.setName("ROLE_ADMIN");
		Role roleUser = new Role();
		roleUser.setId(2L);
		roleUser.setName("ROLE_USER");
		
		Assertions.assertFalse(roleAdmin.getName().equals(roleUser.getName()));
		Assertions.assertTrue(Long.compare(roleAdmin.getId(), roleUser.getId()) !=0);
	}

}
