package com.globant.bootcamp.EggsShopping.models.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.enums.Color;

class InvoiceItemTest {

	@Test
	void test() {
		Egg egg1 = new Egg();
		egg1.setColor(Color.STRING_RED);
		egg1.setId(1L);
		Egg egg2 = new Egg();
		egg2.setColor(Color.STRING_RED);
		egg2.setId(2L);
		Egg egg3 = new Egg();
		egg3.setColor(Color.STRING_RED);
		egg3.setId(1L);
		Egg egg4 = new Egg();
		egg4.setColor(Color.STRING_WHITE);
		egg4.setId(3L);
		Egg egg5 = new Egg();
		egg5.setColor(Color.STRING_WHITE);
		egg5.setId(4L);
		
		EggsTray eggTray1 = new EggsTray();
		eggTray1.setColor(Color.STRING_RED);
		eggTray1.setId(1L);
		eggTray1.setPrice(35D);
		eggTray1.setSold(Constants.FALSE);
		eggTray1.setEggs(new ArrayList<>());
		egg1.setCarton(eggTray1);
		egg2.setCarton(eggTray1);
		eggTray1.addEgg(egg1);
		eggTray1.addEgg(egg2);
		eggTray1.addEgg(egg3);
		
		EggsTray eggTray2 = new EggsTray();
		eggTray2=eggTray1;
		
		EggsTray eggTray3 = new EggsTray();
		eggTray3.setColor(Color.STRING_RED);
		eggTray3.setId(1L);
		eggTray3.setPrice(35D);
		eggTray3.setSold(Constants.FALSE);
		eggTray3.setEggs(new ArrayList<>());
		eggTray3.addEgg(egg4);
		eggTray3.addEgg(egg5);
		
		
		InvoiceItem item1 = new InvoiceItem();
		InvoiceItem item2 = new InvoiceItem();
		InvoiceItem item3 = new InvoiceItem();
		InvoiceItem item4 = new InvoiceItem();
		InvoiceItem item5 = new InvoiceItem();
		InvoiceItem item6 = new InvoiceItem();
		InvoiceItem item7 = new InvoiceItem();
		
		item1.setCartons(new ArrayList<>());
		item1.setId(1L);
		item1.setQuantity(3);
		item1.addCarton(eggTray1);
		List<EggsTray> trays = new ArrayList<>();
		trays.add(eggTray2);
		trays.add(eggTray3);
		item1.addCartons(trays);
		
		item2.setCartons(new ArrayList<>());
		item2.setId(1L);
		item2.setQuantity(3);
		item2.addCarton(eggTray1);
		item1.addCartons(trays);		
		
		item3.setCartons(new ArrayList<>());
		item3.setId(2L);
		item3.setQuantity(0);
		
		item4 = item1;
		
		item5.setId(1L);
		item5.setQuantity(3);
		
		item6.setCartons(new ArrayList<>());
		item6.setId(1L);
		item6.setQuantity(3);
		
		item7.setCartons(new ArrayList<>());
		item7.setId(1L);
		item7.setQuantity(4);
		
		Assertions.assertEquals(1L, item1.getId());
		Assertions.assertEquals(35D*3, item1.calculateAmount());
		Assertions.assertEquals(3, item1.getQuantity());

		Assertions.assertTrue(item1.getCartons().getClass().equals(item2.getCartons().getClass()));

		Assertions.assertFalse(item1.equals(item2));
		Assertions.assertFalse(item1.equals(null));
		Assertions.assertTrue(item1.equals(item4));
		Assertions.assertFalse(item1.equals(eggTray3));
		Assertions.assertFalse(item1.equals(item3));
		Assertions.assertFalse(item1.equals(item5));
		Assertions.assertFalse(item1.equals(item6));
		Assertions.assertFalse(item1.equals(item7));
		
		Assertions.assertFalse(item1.hashCode() == item2.hashCode());
		Assertions.assertTrue(item1.hashCode() != item7.hashCode());

	}

}
