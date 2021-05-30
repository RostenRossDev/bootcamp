package com.globant.bootcamp.EggsShopping.models.service;

import java.util.List;

import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.entity.EggsTray;

public interface IEggsTrayService {

	public List<EggsTray> findByColorAndSold(Color color, Boolean sold, Integer quantity);
	
	public List<EggsTray> findBySold(Boolean sold);
	
	public List<EggsTray> findByColor(Color color);
	
	public List<EggsTray> findAll();
	
	public List<EggsTray> updateEggsTray(List<EggsTray> trays);
	
	public List<EggsTray> saveTrayEggs( List<EggsTray> trays);
	
	public List<EggsTray> findAllByColorAndSold(Color color, Boolean sold);
	
	public List<EggsTray> findByStock();
	
	public List<EggsTray> findByStockByColor(Color color);


}
