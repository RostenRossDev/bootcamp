package com.globant.bootcamp.EggsShopping.models.service;

import java.util.List;

import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.EggsTray;

public interface IEggsTrayService {

	public List<EggsTray> findByColorAndSold(Color color, Boolean sold);
	
}
