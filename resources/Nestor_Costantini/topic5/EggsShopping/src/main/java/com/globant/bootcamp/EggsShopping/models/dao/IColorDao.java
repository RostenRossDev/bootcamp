package com.globant.bootcamp.EggsShopping.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.globant.bootcamp.EggsShopping.models.entity.Color;

@Repository
public interface IColorDao extends CrudRepository<Color, Long>{

	public Color findByColor(String color);
	
	public List<Color> findAllByColor(String color);
	
}
