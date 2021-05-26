package com.globant.bootcamp.EggsShopping.models.service;

import com.globant.bootcamp.EggsShopping.models.entity.User;

public interface IUserService {
	
	public User findByUsername(String username);
	
}
