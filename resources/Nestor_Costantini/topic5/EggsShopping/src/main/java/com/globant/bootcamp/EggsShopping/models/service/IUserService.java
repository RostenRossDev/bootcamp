package com.globant.bootcamp.EggsShopping.models.service;

import java.util.List;

import com.globant.bootcamp.EggsShopping.models.entity.User;

public interface IUserService {
	
	public User findByUsername(String username);
		
	public User findById(Long id);
	
	public User createUser(User user);

	public User update (User user);
	
	public void delete(User user);
	
	public List<User> allUsers() ;
	
	public User findByNickName(String nickname);
}
