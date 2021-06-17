package com.globant.bootcamp.EggsShopping.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import com.globant.bootcamp.EggsShopping.models.Repository.IUserDao;
import com.globant.bootcamp.EggsShopping.models.entity.User;

@Service
public class UserService implements IUserService, UserDetailsService{ 
	
	private final Log LOG = LogFactory.getLog(this.getClass());	
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userDao.findByUsername(username);
		LOG.info("username: "+username);
		LOG.info("user: "+user);
		if(user == null) {
			LOG.info("User "+username+" don`t exist!");
			throw new UsernameNotFoundException("User "+username+" don`t exist!");
		}
		List<GrantedAuthority> authorities= user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> LOG.info("Role: "+authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}

    @Transactional(readOnly = true)
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}
	
    @Transactional(readOnly = true)
	@Override
	public User findById(Long id) {
		User user =userDao.findById(id).orElse(null);
		return user;
	}
	
	@Transactional
	@Override
	public User createUser(User user) {
		return userDao.save(user);
	}

	@Transactional
	@Override
	public User update (User user) {
		
		return userDao.save(user);
	}
	
	@Transactional
	@Override
	public void delete(User user) {
		userDao.delete(user);
	}
	
    @Transactional(readOnly = true)
	@Override
	public List<User> allUsers() {
		return (List<User>) userDao.findAll();
	}
	
    @Transactional(readOnly = true)
	@Override
	public User findByNickName(String nickname) {

		return userDao.findByNickname(nickname);	
	}
}
