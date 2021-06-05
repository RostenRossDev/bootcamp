package com.globant.bootcamp.EggsShopping.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.bootcamp.EggsShopping.models.Repository.IColorDao;
import com.globant.bootcamp.EggsShopping.models.entity.Color;


@Service
public class ColorService implements IColorService{

	@Autowired
	IColorDao colorDao;
	
	@Override
	public Color findByColor(String color) {
		// TODO Auto-generated method stub
		return colorDao.findByColor(color.toUpperCase());
	}
	
	@Override
	public List<Color> findAllByColor(String color) {
		// TODO Auto-generated method stub
		return colorDao.findAllByColorfindByColorContaining(color.toUpperCase());
	}

	@Override
	public Color findById(Long id) {
		// TODO Auto-generated method stub
		return colorDao.findById(id).orElse(null);
	}

}
