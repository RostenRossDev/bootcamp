package com.globant.bootcamp.EggsShopping.models.entity;


import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.globant.bootcamp.EggsShopping.constants.Constants;
//import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.constants.StringConstans;

import io.swagger.annotations.Contact;

class EggsTrayTest {

	private EggsTray newEggsTrayRed;
	private EggsTray newEggsTrayWhite;
	
	@Test
	void test() {
		Color colorRed = Color.builder().color(StringConstans.RED).enable(Constants.TRUE).id(1L).build();
		
		Color colorWhite = Color.builder().color(StringConstans.WHITE).enable(Constants.TRUE).id(2L).build();
	
		newEggsTrayRed = EggsTray.builder().eggs(new ArrayList<>()).id(1L).color(colorRed).price(35D)
				.sold(Constants.FALSE).build();
		newEggsTrayWhite = EggsTray.builder().eggs(new ArrayList<>()).id(2L).color(colorWhite).price(30D)
				.sold(Constants.FALSE).build();
		
		
		Egg egg =Egg.builder().color(colorRed).build();
		
		Egg egg2 =Egg.builder().color(colorWhite).build();		
		
		newEggsTrayWhite.addEgg(egg2);
		
		newEggsTrayRed.addEgg(egg);

		
		EggsTray newEggsTrayRed2 = newEggsTrayRed;
		
		EggsTray newEggsTrayRed3 = EggsTray.builder().id(1L).color(colorRed).eggs(new ArrayList<>())
				.price(35D).sold(Constants.FALSE).build();
		
		EggsTray newEggsTrayRed4 = EggsTray.builder().id(1L).color(null).eggs(new ArrayList<>())
				.price(35D).sold(Constants.FALSE).build();
		
		EggsTray newEggsTrayRed5 = EggsTray.builder().id(1L).color(colorRed).eggs(new ArrayList<>())
				.price(30D).sold(Constants.FALSE).build();
		
		EggsTray newEggsTrayRed6 = EggsTray.builder().id(1L).color(colorRed).eggs(new ArrayList<>())
				.price(35D).sold(Constants.TRUE).build();
		
		EggsTray newEggsTrayRed7 = EggsTray.builder().id(1L).color(colorRed).eggs(new ArrayList<>())
				.price(35D).sold(Constants.FALSE).build();
		
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
