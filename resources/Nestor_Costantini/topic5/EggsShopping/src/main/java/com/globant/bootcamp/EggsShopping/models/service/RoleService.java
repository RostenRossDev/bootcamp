package com.globant.bootcamp.EggsShopping.models.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.bootcamp.EggsShopping.models.dao.IRoleDao;
import com.globant.bootcamp.EggsShopping.models.entity.Role;

@Service
public class RoleService implements IRoleService{

	@Autowired
	IRoleDao roleDao;
	
	@Override
	public Role findOne(Long id) {
		 Optional<Role> role =roleDao.findById(id);
		return role.get();
	}

}
