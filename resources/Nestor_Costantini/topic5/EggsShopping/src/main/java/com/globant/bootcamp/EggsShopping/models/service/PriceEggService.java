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
	public EggsPrice updatePrice(EggsPrice toPersist, EggsPrice  disablepRice) {
		
    	//priceDao.disableColor(Boolean.FALSE, color, Boolean.TRUE);
    	
    	/*EggsPrice toPersist = EggsPrice.builder().actual(Constants.TRUE).color(color)
				.description(description).price(price)
				.build();
    	*/
    	//LOG.info("toPesist: "+toPersist);
    	priceDao.save(disablepRice);
    	
    	EggsPrice persistedPrice = priceDao.save(toPersist); 
    	
    	LOG.info("persist: "+persistedPrice);
    	
    	return  persistedPrice;
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


