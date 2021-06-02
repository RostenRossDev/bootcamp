package com.globant.bootcamp.EggsShopping.models.service;

import java.util.List;

import com.globant.bootcamp.EggsShopping.models.entity.Color;

public interface IColorService {

	public Color findByColor (String color);
	
	public List<Color> findAllByColor (String color);
	
	public Color findById (Long id);
}
