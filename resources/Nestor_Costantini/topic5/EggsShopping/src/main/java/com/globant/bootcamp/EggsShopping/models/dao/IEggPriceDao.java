package com.globant.bootcamp.EggsShopping.models.dao;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globant.bootcamp.EggsShopping.models.entity.Color;
//import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.entity.EggsPrice;

@Repository
public interface IEggPriceDao extends JpaRepository<EggsPrice, Long> {

	//@Query("select p from PriceEggs p where p.actual = actual and p.color = color")

	public List<EggsPrice> findByColor(Color color);
	
	public List<EggsPrice> findByActual(Boolean actual);
	
	//public PriceEggs findByColorId(Long id);

	public EggsPrice findByColorAndActual(Color color, Boolean actual);
	
	
}
