package com.globant.bootcamp.EggsShopping.models.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.dao.IEggsTrayDao;
import com.globant.bootcamp.EggsShopping.models.entity.EggsTray;

@Service
public class EggsTrayService implements IEggsTrayService{
	private final Log LOG  = LogFactory.getLog(this.getClass());

	@Autowired
	IEggsTrayDao eggsTrayDao;
	
	@Override
	public List<EggsTray> findByColorAndSold(Color color, Boolean sold, Integer quantity) {
		List<EggsTray> eggsTraysByQuantity =new ArrayList<EggsTray>();
		List<EggsTray> eggsTrays = eggsTrayDao.findBySoldAndColor(sold, color);
		LOG.info("array 2 : "+ eggsTrays.size());
		
		
		for (int i = 0; i < quantity && i< eggsTrays.size(); i++) {
			eggsTraysByQuantity.add(eggsTrays.get(i));
		}
		
		List<EggsTray> soldEggsTrays =new ArrayList<EggsTray>();
		for (EggsTray eggsTray : eggsTraysByQuantity) {
			eggsTray.setSold(true);
			soldEggsTrays.add(eggsTray);
		}
		
		eggsTrayDao.saveAll(soldEggsTrays);
		return soldEggsTrays;
	}

	@Override
	public List<EggsTray> findAllByColorAndSold(Color color, Boolean sold) {
		List<EggsTray> eggsTrayrs = eggsTrayDao.findBySoldAndColor(sold, color);
		
		
		return eggsTrayrs;
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
	public List<EggsTray> findByStock() {
		return eggsTrayDao.findBySold(Constants.FALSE);
	}
	
	@Override
	public List<EggsTray> updateEggsTray(List<EggsTray> trays) {
		// TODO Auto-generated method stub
		
		return (List<EggsTray>) eggsTrayDao.saveAll(trays);
	}

	@Override
	public List<EggsTray> saveTrayEggs(List<EggsTray> trays) {
		// TODO Auto-generated method stub
		return (List<EggsTray>) eggsTrayDao.saveAll(trays);
	}

	@Override
	public List<EggsTray> findByStockByColor(Color color) {
		List<EggsTray> eggsTrayrs = eggsTrayDao.findBySoldAndColor(Constants.FALSE,color);
		
		return eggsTrayrs;
	}

	
	
}
