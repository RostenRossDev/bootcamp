package com.globant.bootcamp.EggsShopping.models.dao;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.entity.PriceEggs;

@Repository
public interface IEggPriceDao extends CrudRepository<PriceEggs, Long> {

	//@Query("select p from PriceEggs p where p.actual = actual and p.color = color")
	public PriceEggs findByActualAndColor(Boolean actual, Color color);


	public PriceEggs findByActual(Boolean actual);
}
