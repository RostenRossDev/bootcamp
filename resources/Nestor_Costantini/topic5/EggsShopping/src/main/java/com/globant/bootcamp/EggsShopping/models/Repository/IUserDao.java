package com.globant.bootcamp.EggsShopping.models.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.globant.bootcamp.EggsShopping.models.entity.User;

@Repository
public interface IUserDao extends CrudRepository<User, Long>{
	
	public User findByUsername(String username);
	
	public User findByNickname(String nickname);
	
	
}
