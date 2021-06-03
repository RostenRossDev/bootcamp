package com.globant.bootcamp.EggsShopping.models.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.bootcamp.EggsShopping.models.Repository.IEggDao;
import com.globant.bootcamp.EggsShopping.models.entity.Egg;

@Service
public class EggService implements IEggService {

	@Autowired
	IEggDao eggDao;
	
	@Transactional
	public Egg save(Egg egg) {
		return eggDao.save(egg);
	}
	
}
