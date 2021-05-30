package com.globant.bootcamp.EggsShopping.models.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.dao.IEggPriceDao;
import com.globant.bootcamp.EggsShopping.models.entity.PriceEggs;

@Service
public class PriceEggService {

	private final Log LOG = LogFactory.getLog(this.getClass());	
	
	@Autowired
	IEggPriceDao priceDao;
	
	public Double priceByColor(Color color) {
		PriceEggs price=  priceDao.findByActualAndColor(Constants.TRUE, color);
	
		return price.getPrice();
	}
	
	
	
	public PriceEggs newPrice(Color color, Double price) {
		PriceEggs oldprice=  priceDao.findByActualAndColor(Constants.TRUE, color);
	
		oldprice.setActual(false);
		priceDao.save(oldprice);
		PriceEggs newPrice = new PriceEggs();
		newPrice.setActual(true);
		newPrice.setColor(oldprice.getColor());
		newPrice.setDescription(oldprice.getDescription());
		newPrice.setPrice(price);
		
		return priceDao.save(newPrice);
	}
	
	
}
