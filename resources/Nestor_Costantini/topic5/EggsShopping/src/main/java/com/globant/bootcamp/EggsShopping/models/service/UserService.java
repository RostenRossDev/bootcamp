package com.globant.bootcamp.EggsShopping.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globant.bootcamp.EggsShopping.models.dao.IUserDao;
import com.globant.bootcamp.EggsShopping.models.entity.User;

@Service
public class UserService implements IUserService, UserDetailsService{ 
	
	private Logger logger = LoggerFactory.getLogger(UserService.class); 
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userDao.findByUsername(username);
		
		if(user == null) {
			logger.info("User "+username+" don`t exist!");
			throw new UsernameNotFoundException("User "+username+" don`t exist!");
		}
		List<GrantedAuthority> authorities= user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> logger.info("Role: "+authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}

	
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}
	
	public User findById(Long id) {
		User user =userDao.findById(id).orElse(null);
		return user;
	}
	
	public User createUser(User user) {
		return userDao.save(user);
	}

	public User update (User user) {
		
		return userDao.save(user);
	}
	
	public void delete(User user) {
		userDao.delete(user);
	}
	
	public List<User> allUsers() {
		return (List<User>) userDao.findAll();
	}
	
	public User findByNickName(String nickname) {

		return userDao.findByNickname(nickname);	
	}
}
