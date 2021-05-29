package com.globant.bootcamp.EggsShopping.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.EggsTray;
import com.globant.bootcamp.EggsShopping.models.dao.IEggsTrayDao;

public class EggsTrayService implements IEggsTrayService{

	@Autowired
	IEggsTrayDao eggsTrayDao;
	
	@Override
	public List<EggsTray> findByColorAndSold(Color color, Boolean sold) {
		List<EggsTray> eggsTrayrs = eggsTrayDao.findByColorAndSold(color, sold);
		
		return eggsTrayrs;
	}

}
