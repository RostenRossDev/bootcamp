package com.globant.bootcamp.EggsShopping.models.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.globant.bootcamp.EggsShopping.models.entity.Color;

@Repository
@Qualifier("colorDao")
public interface IColorDao extends CrudRepository<Color, Long>{

	public Color findByColor(String color);
	
	public List<Color> findAllByColorContaining(String color);
	
}
