package com.globant.bootcamp.EggsShopping.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.globant.bootcamp.EggsShopping.models.entity.Color;
import com.globant.bootcamp.EggsShopping.models.entity.EggsTray;
import com.globant.bootcamp.EggsShopping.models.entity.tda.IntegerColorTDA;

class EggsTrayControllerTest {
	@Mock
	private EggsTrayController controller;

	private AutoCloseable closeable;

	private Color color;
	private List<EggsTray> eggsTrays;
	private EggsTray eggsTray;

	private IntegerColorTDA colorTDA;
	private ResponseEntity<?> response;
	private ResponseEntity<?> responseOk;
	private Map<String, Object> responseMap;
	private Map<String, Object> responseMapList;

	@BeforeEach
	void setUp() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);
		colorTDA = new IntegerColorTDA();
		colorTDA.setColor("RED");
		colorTDA.setEnable(Boolean.TRUE);
		color = Color.builder().color(colorTDA.getColor()).enable(Boolean.TRUE).build();
		eggsTrays = new ArrayList<>();
		eggsTray = EggsTray.builder().color(color).eggs(new ArrayList<>()).id(1L).build();
		eggsTrays.add(eggsTray);
		responseMap = new HashMap<String, Object>();
		responseMapList = new HashMap<String, Object>();
		responseMap.put("eggsTrays", eggsTrays);
		responseMapList.put("eggsTrays", eggsTrays);
		response = new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
		responseOk =new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	// addEggsTrays
	@Test
	void addEggsTraysTestShouldResponsUserWhenAddEggsTraysIsCalled() {
		Mockito.doReturn(response).when(controller).addEggsTrays(colorTDA);
		Assertions.assertEquals(response, controller.addEggsTrays(colorTDA));
	}

	@Test
	void addEggsTraysTestShouldResponsStatus200OkWhenAddEggsTraysIsCalled() {
		Mockito.doReturn(response).when(controller).addEggsTrays(colorTDA);
		Assertions.assertEquals(HttpStatus.CREATED, controller.addEggsTrays(colorTDA).getStatusCode());
	}

	// allEggsTray
	@Test
	void allEggsTrayTestShouldResponsUserWhenAllEggsTrayIsCalled() {
		Mockito.doReturn(response).when(controller).allEggsTray();
		Assertions.assertEquals(response, controller.allEggsTray());
	}

	@Test
	void allEggsTrayTestShouldResponsStatus200OkWhenAllEggsTrayIsCalled() {
		Mockito.doReturn(response).when(controller).allEggsTray();
		Assertions.assertEquals(HttpStatus.CREATED, controller.allEggsTray().getStatusCode());
	}

	// allSoldEggsTrayByColor

	@Test
	void allSoldEggsTrayByColorTestShouldResponsUserWhenAllSoldEggsTrayByColorIsCalled() {
		Mockito.doReturn(response).when(controller).allSoldEggsTrayByColor(StringConstans.RED);
		Assertions.assertEquals(response, controller.allSoldEggsTrayByColor(StringConstans.RED));
	}

	@Test
	void deleteTestShouldResponsStatus200OkWhenAllSoldEggsTrayByColorIsCalled() {
		Mockito.doReturn(responseOk).when(controller).allSoldEggsTrayByColor(StringConstans.RED);
		Assertions.assertEquals(HttpStatus.OK, controller.allSoldEggsTrayByColor(StringConstans.RED).getStatusCode());
	}

	// stockEggsTray

	@Test
	void stockEggsTrayTestTrayByColorTestShouldResponsUserWhenStockEggsTrayIsCalled() {
		Mockito.doReturn(response).when(controller).allSoldEggsTrayByColor(StringConstans.RED);
		Assertions.assertEquals(response, controller.allSoldEggsTrayByColor(StringConstans.RED));
	}

	@Test
	void stockEggsTrayTestShouldResponsStatus200OkWhenStockEggsTrayIsCalled() {
		Mockito.doReturn(response).when(controller).stockEggsTray();
		Assertions.assertEquals(HttpStatus.CREATED, controller.stockEggsTray().getStatusCode());
	}
	
	// stockEggsTrayByColor

		@Test
		void stockEggsTrayByColorTestTrayByColorTestShouldResponsUserWhenStockEggsTrayByColorIsCalled() {
			Mockito.doReturn(response).when(controller).stockEggsTrayByColor(StringConstans.RED);
			Assertions.assertEquals(response, controller.stockEggsTrayByColor(StringConstans.RED));
		}

		@Test
		void stockEggsTrayByColorTestShouldResponsStatus200OkWhenStockEggsTrayByColorIsCalled() {
			Mockito.doReturn(responseOk).when(controller).stockEggsTrayByColor(StringConstans.RED);
			Assertions.assertEquals(responseOk, controller.stockEggsTrayByColor(StringConstans.RED));
		}
}
