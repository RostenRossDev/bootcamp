package com.globant.bootcamp.EggsShopping.models.entity;


import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.enums.Color;

class EggsTrayTest {

	private EggsTray newEggsTrayRed = new EggsTray();
	private EggsTray newEggsTrayWhite = new EggsTray();
	
	@Test
	void test() {
		
		newEggsTrayRed.setEggs(new ArrayList<Egg>());
		newEggsTrayWhite.setEggs(new ArrayList<Egg>());
		newEggsTrayWhite.setId(2L);
		newEggsTrayWhite.setColor(Color.STRING_WHITE);

		Egg egg =new Egg();
		Egg egg2 =new Egg();
		egg.setColor(Color.STRING_RED);
		egg2.setColor(Color.STRING_RED);
		newEggsTrayWhite.addEgg(egg);
		newEggsTrayWhite.setPrice(30D);
		newEggsTrayWhite.setSold(Constants.FALSE);
		
		newEggsTrayRed.setId(1L);
		newEggsTrayRed.setColor(Color.STRING_RED);
		newEggsTrayRed.setEggs(new ArrayList<>());
		newEggsTrayRed.setPrice(35D);
		newEggsTrayRed.setSold(Constants.FALSE);

		newEggsTrayRed.addEgg(new Egg());

		
		EggsTray newEggsTrayRed2 = newEggsTrayRed;
		EggsTray newEggsTrayRed3 = new EggsTray();
		EggsTray newEggsTrayRed4 = new EggsTray();
		EggsTray newEggsTrayRed5 = new EggsTray();
		EggsTray newEggsTrayRed6 = new EggsTray();
		EggsTray newEggsTrayRed7 = new EggsTray();
		
		newEggsTrayRed3.setId(1L);
		newEggsTrayRed3.setColor(Color.STRING_RED);
		newEggsTrayRed3.setEggs(new ArrayList<>());
		newEggsTrayRed3.setPrice(35D);
		newEggsTrayRed3.setSold(Constants.FALSE);
		
		newEggsTrayRed4.setId(1L);
		newEggsTrayRed4.setColor(null);
		newEggsTrayRed4.setEggs(new ArrayList<>());
		newEggsTrayRed4.setPrice(35D);
		newEggsTrayRed4.setSold(Constants.FALSE);
		
		newEggsTrayRed4.setId(1L);
		newEggsTrayRed4.setColor(null);
		newEggsTrayRed4.setEggs(new ArrayList<>());
		newEggsTrayRed4.setPrice(30D);
		newEggsTrayRed4.setSold(Constants.FALSE);
		
		newEggsTrayRed5.setId(1L);
		newEggsTrayRed5.setColor(Color.STRING_RED);
		newEggsTrayRed5.setEggs(new ArrayList<>());
		newEggsTrayRed5.setPrice(30D);
		newEggsTrayRed5.setSold(Constants.FALSE);
		
		newEggsTrayRed6.setId(1L);
		newEggsTrayRed6.setColor(Color.STRING_RED);
		newEggsTrayRed6.setEggs(new ArrayList<>());
		newEggsTrayRed6.setPrice(35D);
		newEggsTrayRed6.setSold(Constants.TRUE);
		
		newEggsTrayRed7.setId(1L);
		newEggsTrayRed7.setColor(Color.STRING_RED);
		newEggsTrayRed7.setEggs(new ArrayList<>());
		newEggsTrayRed7.setPrice(35D);
		newEggsTrayRed7.setSold(Constants.FALSE);
		newEggsTrayRed7.addEgg(egg2);

		Assertions.assertFalse(newEggsTrayRed.equals(null));
		Assertions.assertTrue(newEggsTrayRed == newEggsTrayRed2);
		Assertions.assertFalse(newEggsTrayRed.equals(newEggsTrayWhite));
		Assertions.assertFalse(newEggsTrayRed.equals(newEggsTrayRed4));
		Assertions.assertTrue(newEggsTrayRed3.equals( newEggsTrayRed));
		Assertions.assertFalse(newEggsTrayRed.equals(newEggsTrayRed5));
		
		Assertions.assertFalse(newEggsTrayRed.equals(egg));
		Assertions.assertTrue(newEggsTrayRed.equals(newEggsTrayRed2));
		Assertions.assertFalse(newEggsTrayRed.equals(newEggsTrayRed6));
		Assertions.assertFalse(newEggsTrayRed.equals(newEggsTrayRed7));

		
			
	}

}
