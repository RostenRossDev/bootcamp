package com.globant.bootcamp.EggsShopping.models.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.bootcamp.EggsShopping.models.dao.IEggDao;
import com.globant.bootcamp.EggsShopping.models.entity.Egg;

@Service
public class EggService implements IEggService {

	@Autowired
	IEggDao eggDao;
	
	@Transactional
	public void save(Egg egg) {
		eggDao.save(egg);
	}
	
}
