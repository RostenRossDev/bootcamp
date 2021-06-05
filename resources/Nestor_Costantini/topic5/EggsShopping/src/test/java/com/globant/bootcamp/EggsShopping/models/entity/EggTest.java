package com.globant.bootcamp.EggsShopping.models.entity;



import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.constants.StringConstans;
//import com.globant.bootcamp.EggsShopping.enums.Color;

class EggTest {
	private Log LOG = LogFactory.getLog(this.getClass());
	private EggsTray tray1 ;
	private EggsTray tray2;
	private EggsTray tray3;
	private Egg eggRed;
	private Egg eggRed2;
	private Egg eggRed3;
	private Egg eggRed4;
	private Egg eggRed5;
	private Egg eggRed6;
	private Egg eggRed7;
	private Egg eggWhite;
	 
	
	@Test
	void test() {
		Color colorRed = Color.builder().color("RED").enable(Constants.TRUE).id(1L).build();
	
		Color colorWhite = Color.builder().color("WHITE").enable(Constants.TRUE).id(2L).build();
		
		eggRed = Egg.builder().id(1L).color(colorRed).build();
		eggRed3 = Egg.builder().id(2L).color(colorRed).build();
		eggRed4 = Egg.builder().id(1L).color(colorWhite).build();
		eggRed5= Egg.builder().id(1L).color(colorRed).build();
		eggRed6= Egg.builder().id(1L).color(colorWhite).build();
		eggRed7= Egg.builder().id(1L).color(colorWhite).build();

		tray1 = EggsTray.builder().color(colorRed).eggs(new ArrayList<>()).sold(Constants.FALSE).build();
		tray2 = EggsTray.builder().color(colorRed).eggs(new ArrayList<>()).sold(Constants.FALSE).build();
		tray3 = EggsTray.builder().color(colorWhite).eggs(new ArrayList<>()).sold(Constants.FALSE).build();
		
		eggRed.setCarton(tray1);
		eggRed3.setCarton(tray1);
		eggRed4.setCarton(tray1);
		eggRed5.setCarton(tray2);
		eggRed6.setCarton(tray3);
		eggRed7.setCarton(tray1);
		eggRed2=eggRed;
		
		tray1.addEgg(eggRed);
		tray2.addEgg(eggRed);
		tray3.addEgg(eggRed6);
		
		
		Assertions.assertEquals(1L, eggRed.getId());
		Assertions.assertEquals(colorRed, eggRed.getColor());
		Assertions.assertEquals(tray1, eggRed.getCarton());
		LOG.info("1");

		Assertions.assertFalse(eggRed.equals(null));
		LOG.info("2");

		Assertions.assertEquals(eggRed3, eggRed);
		LOG.info("3");

		Assertions.assertNotEquals(eggRed4, eggRed);
		
		LOG.info("4");
		LOG.info("5");

		Assertions.assertFalse(eggRed.equals(tray2) );
		LOG.info("6");

		Assertions.assertEquals(eggRed2, eggRed);
		LOG.info("7");

		Assertions.assertTrue(eggRed3.equals(eggRed));
		LOG.info("8");

		Assertions.assertFalse(eggRed.equals(tray1));
		LOG.info("9");

		Assertions.assertTrue(eggRed.equals(eggRed2));

		eggRed.setColor(null);
		eggRed2.setColor(null);
		
		Assertions.assertTrue(eggRed.equals(eggRed2));
		LOG.info("eggred5");

		eggRed.setColor(colorRed);
		eggWhite.setColor(colorWhite);

		
		Assertions.assertFalse(eggRed.equals(eggRed7));
		Assertions.assertFalse(eggRed.equals(eggRed5));
		

	}
	
}
