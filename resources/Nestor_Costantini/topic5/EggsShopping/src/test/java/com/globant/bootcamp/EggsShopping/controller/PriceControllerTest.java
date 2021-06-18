package com.globant.bootcamp.EggsShopping.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import com.globant.bootcamp.EggsShopping.constants.StringConstans;
import com.globant.bootcamp.EggsShopping.models.Repository.IUserDao;
import com.globant.bootcamp.EggsShopping.models.entity.Color;
import com.globant.bootcamp.EggsShopping.models.entity.EggsPrice;
import com.globant.bootcamp.EggsShopping.models.entity.User;
import com.globant.bootcamp.EggsShopping.models.entity.tda.PriceTda;

class PriceControllerTest {

	private final Log LOG = LogFactory.getLog(this.getClass());

	@Mock
	private PriceController controller;

	private AutoCloseable closeable;

	private Color color;

	private ResponseEntity<?> response;
	private ResponseEntity<?> response500;
	private Map<String, Object> responseMap;
	private PriceTda priceTda;

	private EggsPrice eggsPrice;

	// private ResponseEntity<?> response500 ;

	@BeforeEach
	void setUp() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);
		color = Color.builder().color(StringConstans.RED).enable(Boolean.TRUE).id(1L).build();
		priceTda = new PriceTda();
		priceTda.setActual(Boolean.TRUE);
		priceTda.setColor(StringConstans.RED);
		priceTda.setDescription("dadasdas");
		eggsPrice = EggsPrice.builder().actual(Boolean.TRUE).color(color).description("dasdas").id(1L).price(35D)
				.build();
		responseMap = new HashMap<String, Object>();
		responseMap.put("price", eggsPrice);
		response = new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		response500 = new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	// create
	@Test
	void createTestShouldResponsUserWhenCreateIsCalled() {
		Mockito.doReturn(response).when(controller).create(priceTda);
		Assertions.assertEquals(response, controller.create(priceTda));
	}

	@Test
	void createTestShouldResponsStatus200OkWhenCreateIsCalled() {
		Mockito.doReturn(response).when(controller).create(priceTda);
		Assertions.assertEquals(HttpStatus.OK, controller.create(priceTda).getStatusCode());
	}

	@Test
	void createTestShouldResponsStatus500InternalServerErrorWhenCreateIsCalled() {
		Mockito.doReturn(response500).when(controller).create(priceTda);
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.create(priceTda).getStatusCode());
	}

	// update
	@Test
	void updateTestShouldResponsUserWhenCreateIsCalled() {
		Mockito.doReturn(response).when(controller).update(priceTda);
		Assertions.assertEquals(response, controller.update(priceTda));
	}

	@Test
	void updateTestShouldResponsStatus200OkWhenCreateIsCalled() {
		Mockito.doReturn(response).when(controller).create(priceTda);
		Assertions.assertEquals(HttpStatus.OK, controller.create(priceTda).getStatusCode());
	}

	@Test
	void updateTestShouldResponsStatus500InternalServerErrorWhenCreateIsCalled() {
		Mockito.doReturn(response500).when(controller).create(priceTda);
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.create(priceTda).getStatusCode());
	}
	
	// delete
	
	@Test
	void deleteTestShouldResponsUserWhenCreateIsCalled() {
		Mockito.doReturn(response).when(controller).update(priceTda);
		Assertions.assertEquals(response, controller.update(priceTda));
	}

	@Test
	void deleteTestShouldResponsStatus200OkWhenCreateIsCalled() {
		Mockito.doReturn(response).when(controller).delete(priceTda);
		Assertions.assertEquals(HttpStatus.OK, controller.delete(priceTda).getStatusCode());
	}

	@Test
	void deleteTestShouldResponsStatus500InternalServerErrorWhenCreateIsCalled() {
		Mockito.doReturn(response500).when(controller).delete(priceTda);
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.delete(priceTda).getStatusCode());
	}

}
