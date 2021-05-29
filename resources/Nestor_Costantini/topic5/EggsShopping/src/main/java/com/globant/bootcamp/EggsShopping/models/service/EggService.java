package com.globant.bootcamp.EggsShopping.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.bootcamp.EggsShopping.models.animals.Egg;
import com.globant.bootcamp.EggsShopping.models.dao.IEggDao;

@Service
public class EggService implements IEggService {

	@Autowired
	IEggDao eggDao;
	
	
	public void save(Egg egg) {
		eggDao.save(egg);
	}
	
}
