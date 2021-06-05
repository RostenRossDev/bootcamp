package com.globant.bootcamp.EggsShopping.models.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.globant.bootcamp.EggsShopping.constants.Constants;

class EggsPriceTest {
	private Log LOG = LogFactory.getLog(this.getClass());

	//@Test
	void test() {
		
		Color color = Color.builder().color("RED").id(1L).build();

		EggsPrice price = EggsPrice.builder().color(color).id(1L).actual(true)
				.color(color).price(35D).description("Red eggs tray").build();
		
		Assertions.assertTrue(Long.compare(1L, price.getId()) == 0);
		Assertions.assertFalse(Long.compare(1L, price.getId()) != 0);
		
		Assertions.assertTrue(color.equals(price.getColor()));
		
		Assertions.assertTrue("Red eggs tray".equals(price.getDescription()) );
		
		Assertions.assertTrue(Double.compare(35D, price.getPrice()) == 0 );
		
		Assertions.assertTrue(Constants.TRUE == price.getActual());
		
	}

}
