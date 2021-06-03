package com.globant.bootcamp.EggsShopping.models.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.globant.bootcamp.EggsShopping.models.Repository.IEggDao;
import com.globant.bootcamp.EggsShopping.models.Repository.IEggsTrayDao;
import com.globant.bootcamp.EggsShopping.models.entity.Color;
import com.globant.bootcamp.EggsShopping.models.entity.Egg;
import com.globant.bootcamp.EggsShopping.models.entity.EggsTray;

class EggsTrayServiceTest {
	@Mock
	private IEggsTrayDao repository;

	@InjectMocks
	private EggsTrayService service;
	
    private AutoCloseable closeable;
    
	private Egg egg;
	
	private Color color;
	
	private EggsTray tray;
	
	private EggsTray anotherTraty;
	
	@BeforeEach
	void setUp() throws Exception {
		closeable =  MockitoAnnotations.openMocks(this);
		tray = new EggsTray();
		anotherTraty = new EggsTray();
		egg = new Egg();
		color = new Color();
		
		color.setColor("RED");
		color.setId(1L);
		color.setEnable(true);
		
		tray.setId(1L);
		tray.setColor(color);
		tray.setSold(false);
		tray.setPrice(35D);
		tray.setEggs(new ArrayList<>());
		tray.addEgg(egg);
		
		anotherTraty.setId(2L);
		anotherTraty.setColor(color);
		anotherTraty.setSold(false);
		anotherTraty.setPrice(35D);
		anotherTraty.setEggs(new ArrayList<>());
		anotherTraty.addEgg(egg);
		
		egg.setId(1L);
		egg.setCarton(tray);
		egg.setColor(color);
		
		

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void saveEggTrayTest() {
		
		given(repository.save(tray)).willReturn(tray);
		
		EggsTray eggTray = service.saveEggTray(tray);
			    
	    assertEquals(tray, eggTray);
	}
	
	@Test
	void updateEggsTrayListTest() {
		
		Collection<EggsTray> trays = List.of(tray, anotherTraty);
		
		given(repository.saveAll(trays)).willReturn(trays);
		
		
		List<EggsTray> traysTest = service.updateEggsTrayList((List)trays);
			    
	    assertEquals(trays, traysTest);
	}
	
	@Test
	void saveTrayEggsTest() {
		
		Collection<EggsTray> eggsTrays = List.of(tray, anotherTraty);
		
		given(repository.saveAll(eggsTrays)).willReturn(List.of(tray, anotherTraty));
		
		List<EggsTray> eggTrayTest = service.saveTrayEggs((List)eggsTrays);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}

	
	@Test
	void findByStockByColorTest() {
		
		Collection<EggsTray> eggsTrays = List.of(tray, anotherTraty);
		
		given(repository.findBySoldAndColor(false, color)).willReturn(List.of(tray, anotherTraty));
		
		List<EggsTray> eggTrayTest = service.findByStockByColor(color);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void findByStockTest() {
		
		Collection<EggsTray> eggsTrays = List.of(tray, anotherTraty);
		
		given(repository.findBySold(false)).willReturn(List.of(tray, anotherTraty));
		
		List<EggsTray> eggTrayTest = service.findByStock();
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void findByColorTest() {
		
		Collection<EggsTray> eggsTrays = List.of(tray, anotherTraty);
		
		given(repository.findByColor(color)).willReturn(List.of(tray, anotherTraty));
		
		List<EggsTray> eggTrayTest = service.findByColor(color);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void findBySoldTest() {
		
		Collection<EggsTray> eggsTrays = List.of(tray, anotherTraty);
		
		given(repository.findBySold(false)).willReturn(List.of(tray, anotherTraty));
		
		List<EggsTray> eggTrayTest = service.findBySold(false);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void findAllTest() {
		
		Collection<EggsTray> eggsTrays = List.of(tray, anotherTraty);
		
		given(repository.findAll()).willReturn(List.of(tray, anotherTraty));
		
		List<EggsTray> eggTrayTest = service.findAll();
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void findAllByColorAndSoldTest() {
		
		Collection<EggsTray> eggsTrays = List.of(tray, anotherTraty);
		
		given(repository.findBySoldAndColor(false, color)).willReturn(List.of(tray, anotherTraty));
		
		List<EggsTray> eggTrayTest = service.findAllByColorAndSold(color, false);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void findByColorAndSoldTest() {
		
		Collection<EggsTray> eggsTrays = List.of(tray, anotherTraty);
		
		given(repository.findBySoldAndColor(false, color)).willReturn(List.of(tray, anotherTraty));
		
		List<EggsTray> eggTrayTest = service.findByColorAndSold(color, false, 2);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
}
