package com.globant.bootcamp.EggsShopping.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.EggsTray;

public interface IEggsTrayDao extends CrudRepository<EggsTray, Long> {
	
	public List<EggsTray> findByColorAndSold(Color color, Boolean sold);

}
