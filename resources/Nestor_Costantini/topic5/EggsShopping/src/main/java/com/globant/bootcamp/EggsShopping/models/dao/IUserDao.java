package com.globant.bootcamp.EggsShopping.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.globant.bootcamp.EggsShopping.models.entity.User;

public interface IUserDao extends CrudRepository<User, Long>{
	
	public User findByUsername(String username);
	
}
