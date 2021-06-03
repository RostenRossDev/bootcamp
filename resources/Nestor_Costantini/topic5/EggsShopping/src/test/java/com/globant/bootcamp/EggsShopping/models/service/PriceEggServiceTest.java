package com.globant.bootcamp.EggsShopping.models.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.globant.bootcamp.EggsShopping.models.Repository.IEggPriceDao;
import com.globant.bootcamp.EggsShopping.models.Repository.IInvoiceDao;
import com.globant.bootcamp.EggsShopping.models.Repository.IUserDao;
import com.globant.bootcamp.EggsShopping.models.entity.Color;
import com.globant.bootcamp.EggsShopping.models.entity.Egg;
import com.globant.bootcamp.EggsShopping.models.entity.EggsPrice;
import com.globant.bootcamp.EggsShopping.models.entity.EggsTray;
import com.globant.bootcamp.EggsShopping.models.entity.Invoice;
import com.globant.bootcamp.EggsShopping.models.entity.User;

class PriceEggServiceTest {
	@Mock
	private IEggPriceDao repository;

	@InjectMocks
	private PriceEggService service;

	private AutoCloseable closeable;

	private Color color;
	
	private EggsPrice price;
	
	@BeforeEach
	void setUp() throws Exception {
		closeable =  MockitoAnnotations.openMocks(this);
		color = new Color();
		color.setColor("RED");
		color.setId(1L);
		color.setEnable(true);
		
		price = new EggsPrice();
		price.setActual(true);
		price.setColor(color);
		price.setDescription("Some text");
		price.setId(1L);
		price.setPrice(35D);
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void priceByColorTest() {

		given(repository.findByColorAndActual(color, true)).willReturn(price);
		
		Double priceD = service.priceByColor(color);
			    
	    assertEquals(35D, priceD);
	}
	
	@Test
	void updatePriceTest() {
		EggsPrice updatePrice = new EggsPrice();
		updatePrice.setActual(true);
		updatePrice.setColor(color);
		updatePrice.setDescription("Some text");
		updatePrice.setId(2L);
		updatePrice.setPrice(40D);
				
		given(repository.findByColorAndActual(color, true)).willReturn(price);
		
		given(repository.save(price)).willReturn(price);
		
		given(repository.save(updatePrice)).willReturn(updatePrice);
		
		EggsPrice priceTest = service.updatePrice(color, 40D);
			    
	    assertNotEquals(price, priceTest);
	}


}
