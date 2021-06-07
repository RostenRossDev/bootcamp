package com.globant.bootcamp.EggsShopping.models.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globant.bootcamp.EggsShopping.models.Repository.IRoleDao;
import com.globant.bootcamp.EggsShopping.models.entity.Role;

@Service
public class RoleService implements IRoleService{

	@Autowired
	IRoleDao roleDao;
	
    @Transactional(readOnly = true)
	@Override
	public Role findOne(Long id) {
    	return roleDao.findById(id).orElse(null);
	}
    
    @Transactional
    public Role createRole(Role role) {
    	
    	return roleDao.save(role); 
    }	

}
