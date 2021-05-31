package com.globant.bootcamp.EggsShopping.models.entity;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.globant.bootcamp.EggsShopping.constants.Constants;

class UserTest {

	@Test
	void test() {

		User user1 = new User();
		User user2 = new User();
		user1.setId(1L);
		user1.setUsername("RostenRoss");
		user1.setNickname("Nestor");
		user1.setPassword("12345");
		user1.setEnabled(Constants.TRUE);
		
		user2.setId(1L);
		user2.setUsername("Rosten Ross");
		user2.setNickname("Nestor Matias");
		user2.setPassword("123456");
		user2.setEnabled(Constants.FALSE);
		user2.setRoles(new ArrayList<>());
		
		Role role = new Role();
		role.setId(1L);
		role.setName("ROLE_USER");
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		user1.setRoles(roles);
		
		Assertions.assertTrue(user1.getEnabled() == Constants.TRUE);
		
		Assertions.assertTrue(user1.getUsername().equals("RostenRoss"));
		Assertions.assertTrue(user1.getNickname().equals("Nestor"));
		Assertions.assertTrue(user1.getPassword().equals("12345"));
		Assertions.assertTrue(Long.compare(1L, user1.getId()) == 0);
		Assertions.assertTrue(user1.getRoles().equals(roles));

		user1.updateUser(user2);
		
		Assertions.assertFalse(user1.getNickname().equals("Nestor"));
		Assertions.assertFalse(user1.getUsername().equals("RostenRoss"));
		Assertions.assertFalse(user1.getPassword().equals("12345"));
		Assertions.assertFalse(user1.getEnabled() == Constants.TRUE);
		
	}
}
