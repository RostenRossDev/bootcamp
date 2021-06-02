package com.globant.bootcamp.EggsShopping.models.dao;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.globant.bootcamp.EggsShopping.models.entity.Color;
//import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.entity.PriceEggs;

@Repository
public interface IEggPriceDao extends JpaRepository<PriceEggs, Long> {

	//@Query("select p from PriceEggs p where p.actual = actual and p.color = color")

	public List<PriceEggs> findByColor(Color color);
	public List<PriceEggs> findByActual(Boolean actual);
	
	//public PriceEggs findByColorId(Long id);

	public PriceEggs findByColorAndActual(Color color, Boolean actual);
	
	
}
