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
	private EggsTray tray1 = new EggsTray();
	private EggsTray tray2 = new EggsTray();
	private EggsTray tray3 = new EggsTray();
	private Egg eggRed = new Egg();
	private Egg eggRed2 = new Egg();
	private Egg eggRed3 = new Egg();
	private Egg eggRed4 = new Egg();
	private Egg eggRed5 = new Egg();
	private Egg eggRed6 = new Egg();
	private Egg eggRed7 = new Egg();
	private Egg eggWhite = new Egg();
	 
	
	@Test
	void test() {
		Color colorRed = new Color();
		colorRed.setColor("RED");
		colorRed.setEnable(Constants.TRUE);
		colorRed.setId(1L);
		
		Color colorWhite = new Color();
		colorWhite.setColor(StringConstans.WHITE);
		colorWhite.setEnable(Constants.TRUE);
		colorWhite.setId(2L);
		
		eggRed.setId(1L);
		eggRed.setColor(colorRed);
		eggRed.setCarton(tray1);
		eggRed3.setId(1L);
		eggRed3.setColor(colorRed);
		eggRed3.setCarton(tray1);
		eggRed4.setId(2L);
		eggRed4.setColor(colorWhite);
		eggRed4.setCarton(tray1);
		eggRed5.setId(1L);
		eggRed5.setColor(colorRed);
		eggRed5.setCarton(tray2);
		eggRed6.setId(1L);
		eggRed6.setColor(colorWhite);
		eggRed6.setCarton(tray3);
		eggRed7.setId(1L);
		eggRed7.setColor(colorWhite);
		eggRed7.setCarton(tray1);
		eggRed2=eggRed;
		
		tray1.setColor(colorRed);
		tray1.setEggs(new ArrayList<>());
		tray1.addEgg(eggRed);
		tray1.setSold(Constants.FALSE);
		
		eggRed.setCarton(tray1);
		
		tray2.setColor(colorRed);
		tray2.setEggs(new ArrayList<>());
		tray2.addEgg(eggRed);
		tray2.setSold(Constants.TRUE);
		eggRed5.setCarton(tray2);

		tray3.setColor(colorWhite);
		tray3.setEggs(new ArrayList<>());
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
