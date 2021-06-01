package com.globant.bootcamp.EggsShopping.models.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.globant.bootcamp.EggsShopping.models.dao.IColorDao;
import com.globant.bootcamp.EggsShopping.models.entity.Color;

public class ColorService implements IColorService{

	@Autowired
	IColorDao colorDao;
	
	@Override
	public Color findByColor(String color) {
		// TODO Auto-generated method stub
		return colorDao.findByColor(color);
	}

	@Override
	public Color findByIn(Long id) {
		// TODO Auto-generated method stub
		return colorDao.findById(id).orElse(null);
	}

}
