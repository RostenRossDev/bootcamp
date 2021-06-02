package com.globant.bootcamp.EggsShopping.models.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.enums.Color;

class PriceEggsTest {
	private Log LOG = LogFactory.getLog(this.getClass());

	//@Test
	void test() {
		PriceEggs price = new PriceEggs();
		
		price.setActual(Constants.TRUE);
		price.setColor(Color.STRING_RED);
		price.setPrice(35D);
		price.setId(1L); 
		price.setDescription("Red eggs tray");
		
		Assertions.assertTrue(Long.compare(1L, price.getId()) == 0);
		Assertions.assertFalse(Long.compare(1L, price.getId()) != 0);
		
		Assertions.assertTrue(Color.STRING_RED.equals(price.getColor()));
		
		Assertions.assertTrue("Red eggs tray".equals(price.getDescription()) );
		
		Assertions.assertTrue(Double.compare(35D, price.getPrice()) == 0 );
		
		Assertions.assertTrue(Constants.TRUE == price.getActual());
		
	}

}
