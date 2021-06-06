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
    	
		EggsPrice price=  priceDao.findByColorAndActual(color, Constants.TRUE);
				
		return (price != null && price.getPrice()>0 ) ? price.getPrice(): null;
	}
	 
	 
    @Transactional
	public EggsPrice updatePrice(Color color, Double price) {
    	LOG.info("aca 1");
		EggsPrice oldprice=  priceDao.findByColorAndActual(color, Constants.TRUE);
		
    	LOG.info("aca 2 oldprice: "+oldprice);

		oldprice.setActual(false);
		
    	LOG.info("aca 3 oldprice: "+oldprice);

		priceDao.save(oldprice);
		
    	LOG.info("aca 4 ");

		EggsPrice newPrice = EggsPrice.builder()
				.actual(Constants.TRUE).color(oldprice.getColor())
				.description(oldprice.getDescription()).price(price).build();
		
    	LOG.info("aca 5 newPrice: "+newPrice);

		return priceDao.save(newPrice);
	}
	
    @Transactional
    public void deletePriceById(Long id) {
    	
    	priceDao.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<EggsPrice> allEggsPrices(){
    	
    	return priceDao.findAll();
    }
	
    @Transactional(readOnly = true)
    public List<EggsPrice> allEggsPriceByColor(Color color){
    	
    	return priceDao.findByColor(color);
    }
}
