package com.globant.bootcamp.EggsShopping.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.EggsTray;
import com.globant.bootcamp.EggsShopping.models.dao.IEggsTrayDao;

@Service
public class EggsTrayService implements IEggsTrayService{

	@Autowired
	IEggsTrayDao eggsTrayDao;
	
	@Override
	public List<EggsTray> findByColorAndSold(Color color, Boolean sold, Integer quantity) {
		List<EggsTray> eggsTrayrs = eggsTrayDao.findByColorAndSold(color, sold);
		List<EggsTray> eggsTrayrsByQuantity =new ArrayList<EggsTray>();
		
		for (int i = 0; i < quantity; i++) {
			eggsTrayrsByQuantity.add(eggsTrayrs.get(i));
		}
		 
		return eggsTrayrsByQuantity;
	}

	@Override
	public List<EggsTray> findAll() {
		List<EggsTray> eggsTrayrs = (List<EggsTray>) eggsTrayDao.findAll();
		return eggsTrayrs;
	}

	@Override
	public List<EggsTray> findBySold(Boolean sold) {
		List<EggsTray> eggsTrayrs =  eggsTrayDao.findBySold(sold);
		return eggsTrayrs;
	}

	@Override
	public List<EggsTray> findByColor(Color color) {
		// TODO Auto-generated method stub
		List<EggsTray> eggsTrayrs =  eggsTrayDao.findByColor(color);
		return eggsTrayrs;
	}

	@Override
	public List<EggsTray> updateEggsTray(List<EggsTray> trays) {
		// TODO Auto-generated method stub
		
		return (List<EggsTray>) eggsTrayDao.saveAll(trays);
	}

	
	
}
