package com.globant.bootcamp.EggsShopping.models.entity;



import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.globant.bootcamp.EggsShopping.enums.Color;

class EggTest {
	private EggsTray tray1 = new EggsTray();
	private EggsTray tray2 = new EggsTray();
	private Egg eggRed = new Egg();
	private Egg eggRed2 = new Egg();
	private Egg eggWhite = new Egg();
	 
	
	@Test
	void test() {
		
		eggRed.setId(1L);
		eggRed.setColor(Color.STRING_RED);
		tray1.setColor(Color.STRING_RED);
		tray1.setEggs(new ArrayList<>());
		tray1.addEgg(eggRed);
		tray2.setColor(Color.STRING_RED);
		tray2.setEggs(new ArrayList<>());
		tray2.addEgg(eggRed);
		eggRed.setCarton(tray1);
		eggRed2=eggRed;
		Assertions.assertEquals(1L, eggRed.getId());
		Assertions.assertEquals(Color.STRING_RED, eggRed.getColor());
		Assertions.assertEquals(tray1, eggRed.getCarton());
		Assertions.assertFalse(eggRed.equals(tray1));
		Assertions.assertTrue(eggRed.equals(eggRed2));

		eggRed.setColor(null);
		eggRed2.setColor(null);
		Assertions.assertTrue(eggRed.equals(eggRed2));

		eggRed.setColor(Color.STRING_RED);
		eggWhite.setColor(Color.STRING_WHITE);
		Assertions.assertEquals("(D)", eggRed.toString());
		Assertions.assertEquals("(O)", eggWhite.toString());

	}
	
}
