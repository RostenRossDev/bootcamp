package com.globant.bootcamp.EggsShopping.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.globant.bootcamp.EggsShopping.models.entity.Color;

@Repository
public interface IColorDao extends CrudRepository<Color, Long>{

	public Color findByColor(String color);
	
}
