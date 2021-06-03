package com.globant.bootcamp.EggsShopping.models.service;



import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.models.Repository.IEggPriceDao;
import com.globant.bootcamp.EggsShopping.models.entity.Color;
import com.globant.bootcamp.EggsShopping.models.entity.EggsPrice;

@Service
public class PriceEggService {

	private final Log LOG = LogFactory.getLog(this.getClass());	
	
	@Autowired
	IEggPriceDao priceDao;
	
    @Transactional(readOnly = true)
	public Double priceByColor(Color color) {


		LOG.info("color: "+color.getId());
		EggsPrice price=  priceDao.findByColorAndActual(color, Constants.TRUE);
		
		LOG.info("price: "+price);
		return price.getPrice();
	}
	 
	 
    @Transactional
	public EggsPrice updatePrice(Color color, Double price) {
		EggsPrice oldprice=  priceDao.findByColorAndActual(color, Constants.TRUE);
	
		oldprice.setActual(false);
		priceDao.save(oldprice);
		EggsPrice newPrice = new EggsPrice();
		newPrice.setActual(true);
		newPrice.setColor(oldprice.getColor());
		newPrice.setDescription(oldprice.getDescription());
		newPrice.setPrice(price);
		
		return priceDao.save(newPrice);
	}
	
	
}
