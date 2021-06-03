package com.globant.bootcamp.EggsShopping.models.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.globant.bootcamp.EggsShopping.models.entity.Color;
//import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.entity.EggsTray;

@Repository
public interface IEggsTrayDao extends CrudRepository<EggsTray, Long> {
	
	public List<EggsTray> findBySoldAndColor(Boolean sold, Color color);
	
	public List<EggsTray> findBySold(Boolean sold);
	
	public List<EggsTray> findByColor(Color color);
	
}
