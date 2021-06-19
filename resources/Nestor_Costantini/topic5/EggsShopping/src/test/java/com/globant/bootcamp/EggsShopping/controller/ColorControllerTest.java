package com.globant.bootcamp.EggsShopping.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.globant.bootcamp.EggsShopping.constants.StringConstans;
import com.globant.bootcamp.EggsShopping.models.Repository.IColorDao;
import com.globant.bootcamp.EggsShopping.models.entity.Color;
import com.globant.bootcamp.EggsShopping.models.entity.tda.IntegerColorTDA;
import com.globant.bootcamp.EggsShopping.models.service.ColorService;

class ColorControllerTest {
	@Mock
	private ColorController controller;
	
	@Mock
	private IColorDao dao;
	@Mock
	private ColorService service;
	
	private AutoCloseable closeable;

	private Color color;

	private IntegerColorTDA colorTDA;
	private ResponseEntity<?> response;
	private ResponseEntity<?> response500;
	private ResponseEntity<?> responseOk;
	private Map<String, Object> responseMap;

	@BeforeEach
	void setUp() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);
		colorTDA = new IntegerColorTDA();
		colorTDA.setColor("RED");
		colorTDA.setEnable(Boolean.TRUE);
		color = Color.builder().color(colorTDA.getColor()).enable(Boolean.TRUE).build();
		responseMap = new HashMap<String, Object>();
		responseMap.put("color", color);
		response = new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
		responseOk = new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		response500 =new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	// create
	@Test
	void createTestShouldResponsUserWhenCreateIsCalled() {
		Mockito.doReturn(response).when(controller).create(colorTDA);
		Assertions.assertEquals(response, controller.create(colorTDA));
	}

	@Test
	void createTestShouldResponsStatus200OkWhenCreateIsCalled() {
		Mockito.doReturn(response).when(controller).create(colorTDA);
		Assertions.assertEquals(HttpStatus.CREATED, controller.create(colorTDA).getStatusCode());
	}

	@Test
	void createTestShouldResponsStatus500InternalServerErrorWhenCreateIsCalled() {
		Mockito.doReturn(response500).when(controller).create(colorTDA);
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.create(colorTDA).getStatusCode());
	}

	// update
	@Test
	void updateTestShouldResponsUserWhenCreateIsCalled() {
		Mockito.doReturn(response).when(controller).update(colorTDA);
		Assertions.assertEquals(response, controller.update(colorTDA));
	}

	@Test
	void updateTestShouldResponsStatus200OkWhenCreateIsCalled() {
		Mockito.doReturn(response).when(controller).update(colorTDA);
		Assertions.assertEquals(HttpStatus.CREATED, controller.update(colorTDA).getStatusCode());
	}

	@Test
	void updateTestShouldResponsStatus500InternalServerErrorWhenCreateIsCalled() {
		Mockito.doReturn(response500).when(controller).update(colorTDA);
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.update(colorTDA).getStatusCode());
	}
	
	// delete
	
	@Test
	void deleteTestShouldResponsUserWhenCreateIsCalled() {
		Mockito.doReturn(response).when(controller).delete(StringConstans.RED);
		Assertions.assertEquals(response, controller.delete(StringConstans.RED));
	}

	@Test
	void deleteTestShouldResponsStatus200OkWhenCreateIsCalled() {
		Mockito.doReturn(responseOk).when(controller).delete(StringConstans.RED);
		Assertions.assertEquals(HttpStatus.OK, controller.delete(StringConstans.RED).getStatusCode());
	}
	
	@Test
	void deleteTestShouldResponsStatus500InternalServerErrorWhenCreateIsCalled() {
		Mockito.doReturn(response500).when(controller).delete(StringConstans.RED);
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.delete(StringConstans.RED).getStatusCode());
	}

}
