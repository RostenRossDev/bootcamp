package com.globant.bootcamp.EggsShopping.models.Repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globant.bootcamp.EggsShopping.models.entity.Color;
//import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.entity.EggsPrice;
import com.globant.bootcamp.EggsShopping.models.service.PriceEggService;

@Repository
public interface IEggPriceDao extends JpaRepository<EggsPrice, Long> {

	public List<EggsPrice> findByColor(Color color);
	
	public List<EggsPrice> findByActual(Boolean actual);
	
	public EggsPrice findByColorAndActual(Color color, Boolean actual);
	
	@Modifying
	@Query("update PriceEgg p set p.actual = ?1 where p.color = ?2 and p.actual = ?3")
	public EggsPrice disableColor(Boolean actual, Color color, Boolean oldActual);
}
