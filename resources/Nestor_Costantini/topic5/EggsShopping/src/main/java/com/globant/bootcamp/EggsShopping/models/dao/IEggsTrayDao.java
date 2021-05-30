package com.globant.bootcamp.EggsShopping.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.entity.EggsTray;

public interface IEggsTrayDao extends CrudRepository<EggsTray, Long> {
	
	public List<EggsTray> findBySoldAndColor(Boolean sold, Color color);
	
	public List<EggsTray> findBySold(Boolean sold);
	
	public List<EggsTray> findByColor(Color color);
	
}
