package com.globant.bootcamp.EggsShopping.models.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.apache.tomcat.jni.Time;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.constants.StringConstans;
//import com.globant.bootcamp.EggsShopping.enums.Color;

class InvoiceTest {

	//@Test
	void test() {
		Color colorRed = new Color();
		colorRed.setColor(StringConstans.RED);
		colorRed.setEnable(Constants.TRUE);
		colorRed.setId(1L);
		Egg egg1 = new Egg();
		egg1.setColor(colorRed);
		egg1.setId(1L);	
		
		User user1 = new User();
		User user2 = new User();
		user1.setUsername("RostenRoss");
		EggsTray tray1= new EggsTray();
		tray1.setColor(colorRed);
		tray1.setId(1L);
		tray1.setPrice(35D);
		tray1.setSold(Constants.FALSE);
		tray1.setEggs(new ArrayList<>());
		egg1.setCarton(tray1);
		tray1.addEgg(egg1);
		
		Invoice invoice1 = new Invoice();
		Invoice invoice2 = new Invoice();
		Invoice invoice3 = new Invoice();
		Invoice invoice4 = new Invoice();
		Invoice invoice5 = new Invoice();
		Invoice invoice6 = new Invoice();
		
		InvoiceItem item1 = new InvoiceItem();
		InvoiceItem item2 = new InvoiceItem();
		item1.setId(1L);
		item1.setQuantity(1);
		item1.setCartons(new ArrayList<>());
		item1.addCarton(tray1);
		
		item2.setId(2L);
		item2.setQuantity(1);
		item2.setCartons(new ArrayList<>());
		item2.addCarton(tray1);

		invoice1.setId(1L);
		invoice1.setDescription("red eggs");
		invoice1.setCreateAt(new Timestamp(new Date().getTime()));
		invoice1.setItems(new ArrayList<>());
		invoice1.setUser(user1);
		invoice1.addIteminvoice(item1);
		
		invoice2.setId(2L);
		invoice2.setDescription(invoice1.getDescription());
		invoice2.setCreateAt(invoice1.getCreateAt());
		invoice2.setItems(new ArrayList<>());
		invoice2.setUser(invoice1.getUser());
		invoice2.addIteminvoice(item1);
		
		
		try {
			
			Thread.sleep(5000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		invoice3.setId(1L);
		invoice3.setDescription("red eggs");
		invoice3.setCreateAt(new Timestamp(new Date().getTime()));
		invoice3.setItems(new ArrayList<>());
		invoice3.setUser(user1);
		invoice3.addIteminvoice(item1);
		
		invoice4.setId(1L);
		invoice4.setDescription("red eggs tray");
		invoice4.setCreateAt(new Timestamp(new Date().getTime()));
		invoice4.setItems(new ArrayList<>());
		invoice4.setUser(user1);
		invoice4.addIteminvoice(item1);
		
		invoice5.setId(1L);
		invoice5.setDescription("red eggs");
		invoice5.setCreateAt(invoice1.getCreateAt());
		invoice5.setItems(new ArrayList<>());
		invoice5.setUser(user2);
		invoice5.addIteminvoice(item1);
		
		invoice6.setId(1L);
		invoice6.setDescription("red eggs");
		invoice6.setCreateAt(invoice1.getCreateAt());
		invoice6.setItems(new ArrayList<>());
		invoice6.setUser(user1);
		invoice6.addIteminvoice(item2);
		
		Assertions.assertFalse(invoice1.equals(invoice2));
		Assertions.assertFalse(invoice1.equals(null));
		Assertions.assertFalse(invoice1.equals(item1));
		Assertions.assertTrue(invoice1.equals(invoice1));
		Assertions.assertFalse(invoice1.equals(invoice4));
		Assertions.assertFalse(invoice1.equals(invoice3));
		Assertions.assertFalse(invoice1.equals(invoice5));
		Assertions.assertFalse(invoice1.equals(invoice6));

		Assertions.assertNotEquals(20D,invoice1.getTotal());
		
		
	}

}
