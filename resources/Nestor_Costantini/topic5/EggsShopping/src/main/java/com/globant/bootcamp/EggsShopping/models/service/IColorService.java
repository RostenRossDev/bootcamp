package com.globant.bootcamp.EggsShopping.models.service;

import com.globant.bootcamp.EggsShopping.models.entity.Color;

public interface IColorService {

	public Color findByColor (String color);
	
	public Color findByIn (Long id);
}
