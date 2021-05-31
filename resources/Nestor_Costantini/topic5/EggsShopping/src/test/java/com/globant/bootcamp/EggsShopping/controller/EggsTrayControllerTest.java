package com.globant.bootcamp.EggsShopping.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.globant.bootcamp.EggsShopping.models.service.EggsTrayService;
import com.globant.bootcamp.EggsShopping.models.service.InvoiceService;
import com.globant.bootcamp.EggsShopping.models.service.PriceEggService;
import com.globant.bootcamp.EggsShopping.models.service.UserService;

class EggsTrayControllerTest {
	private Log LOG = LogFactory.getLog(this.getClass());
	
	
	EggsTrayService eggTrayService = Mockito.mock(EggsTrayService.class);
	
	@Autowired
	InvoiceService invoiceService;

	@Autowired
	UserService userService;
	
	@Autowired
	PriceEggService priceTrayServie;
	
	@Autowired
	EggsTrayController controller = new EggsTrayController();
	
	@BeforeEach
	void setUp() throws Exception {
		LOG.info("Before test");
	}

	

	@Test
	void test() {
		LOG.info("In test");
		
	}

	@AfterEach
	void tearDown() throws Exception {
		LOG.info("After test");

	}
}
