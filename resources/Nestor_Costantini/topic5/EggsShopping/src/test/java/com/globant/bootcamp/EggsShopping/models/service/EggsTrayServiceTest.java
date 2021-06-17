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

import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.constants.StringConstans;
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
	
	private Color colorRed;
	
	private Color colorBlue;
	
	private EggsTray tray;
	
	private EggsTray anotherTraty;
	
	@BeforeEach
	void setUp() throws Exception {
		closeable =  MockitoAnnotations.openMocks(this);
		
		colorRed = Color.builder().color(StringConstans.RED).id(1L).enable(Constants.TRUE).build();
		
		colorBlue = Color.builder().color(StringConstans.BLUE).id(2L).enable(Constants.TRUE).build();

		egg = Egg.builder().id(1L).color(colorRed).build();
		
		
		tray = EggsTray.builder().id(1L).color(colorRed).sold(Constants.FALSE).price(35D).eggs(new ArrayList<>()).build();

		tray.addEgg(egg);
		
		anotherTraty = EggsTray.builder().id(2L).color(colorRed).sold(Constants.FALSE).price(35D).eggs(new ArrayList<>()).build();

		anotherTraty.addEgg(egg);
		
		egg.setCarton(tray);

	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void saveEggTrayTestShouldReturnEggTrayWhenRepositoryPersist() {
		
		given(repository.save(tray)).willReturn(tray);
		
		EggsTray eggTray = service.saveEggTray(tray);
			    
	    assertEquals(tray, eggTray);
	}
	
	@Test
	void saveEggTrayTestShoulReturnNullWhenRepositorySaveNullEggyTray() {
		
		given(repository.save(null)).willReturn(null);
		
		EggsTray eggTray = service.saveEggTray(null);
			    
	    assertEquals(null, eggTray);
	}
	
	@Test
	void updateEggsTrayListTestShouldReturnUpdatedEggsTrayWhenRepositoryContainsMatches() {
		
		Collection<EggsTray> trays = List.of(tray, anotherTraty);
		
		given(repository.saveAll(trays)).willReturn(trays);
		
		List<EggsTray> traysTest = service.updateEggsTrayList((List)trays);
			    
	    assertEquals(trays, traysTest);
	}
	
	@Test
	void updateEggsTrayListTestShouldReturnEmptyListWhenRepositoryUpdateNullEggTrayList() {
		
		Collection<EggsTray> trays = List.of(tray, anotherTraty);
		Collection<EggsTray> nullTrays = null;
		given(repository.saveAll(trays)).willReturn(nullTrays);
		
		List<EggsTray> traysTest = service.updateEggsTrayList((List)trays);
			    
	    assertEquals(nullTrays, traysTest);
	}
	 
	@Test
	void saveEggTrayListTestShouldReturnNullWhenRepositoryPersistFail() {
		
		Collection<EggsTray> eggsTrays = null;
		
		given(repository.saveAll(eggsTrays)).willReturn(null);
		
		List<EggsTray> eggTrayTest = service.saveTrayEggs((List)eggsTrays);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void saveEggTrayListTestShouldReturnListWhenRepositoryPersistList() {
		
		Collection<EggsTray> eggsTrays = List.of(tray, anotherTraty);
		
		given(repository.saveAll(eggsTrays)).willReturn((List)eggsTrays);
		
		List<EggsTray> eggTrayTest = service.saveTrayEggs((List)eggsTrays);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void findByStockByColorTestShouldReturnColorListWhenRepositoryContainsMatches() {
		
		Collection<EggsTray> eggsTrays = List.of(tray, anotherTraty);
		
		given(repository.findBySoldAndColor(false, colorRed)).willReturn((List)eggsTrays);
		
		List<EggsTray> eggTrayTest = service.findByStockByColor(colorRed);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void findByStockTestTestShouldReturnNullWhenRepositoryNotContainsMatches() {
		
		Collection<EggsTray> eggsTrays = null;
		
		given(repository.findBySold(Constants.FALSE)).willReturn((List)eggsTrays);
		
		List<EggsTray> eggTrayTest = service.findByStock();
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void findByStockTestTesShouldReturnEmptyListWhenRepositoryNotContainsMatches() {
				
		given(repository.findBySold(Constants.FALSE)).willReturn(List.of());
		
		List<EggsTray> eggTrayTest = service.findByStock();
			    
	    assertEquals(List.of(), eggTrayTest);
	}
	
	@Test
	void findByColorTestShouldReturnEggsTrayListWhenRepositoryContainsMatches() {
		
		Collection<EggsTray> eggsTrays = List.of(tray, anotherTraty);
		
		given(repository.findByColor(colorRed)).willReturn((List)eggsTrays);
		
		List<EggsTray> eggTrayTest = service.findByColor(colorRed);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void findByColorTestShouldReturnEmptyEggsTrayListWhenRepositoryNotContainsMatches() {
		
		Collection<EggsTray> eggsTrays = List.of();
		
		given(repository.findByColor(colorBlue)).willReturn((List)eggsTrays);
		
		List<EggsTray> eggTrayTest = service.findByColor(colorBlue);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	
	@Test
	void findBySoldTestShouldReturnSoldEggTrayListWhenRepositoryContainsMatches() {
		
		Collection<EggsTray> eggsTrays = List.of(tray, anotherTraty);
		
		given(repository.findBySold(Constants.FALSE)).willReturn((List)eggsTrays);
		
		List<EggsTray> eggTrayTest = service.findBySold(Constants.FALSE);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void findBySoldTestShouldReturnEmptyEggTrayListWhenRepositoryContainsMatches() {
		
		Collection<EggsTray> eggsTrays = List.of();
		
		given(repository.findBySold(Constants.FALSE)).willReturn((List)eggsTrays);
		
		List<EggsTray> eggTrayTest = service.findBySold(Constants.FALSE);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void findAllTestShouldReturnEggsTrayListWhenRepositoryContainsMatches() {
		
		Collection<EggsTray> eggsTrays = List.of(tray, anotherTraty);
		
		given(repository.findAll()).willReturn(eggsTrays);
		
		List<EggsTray> eggTrayTest = service.findAll();
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void findAllTestShouldReturnEmptyEggsTrayListWhenRepositoryNotContainsMatches() {
		
		Collection<EggsTray> eggsTrays = List.of();
		
		given(repository.findAll()).willReturn(eggsTrays);
		
		List<EggsTray> eggTrayTest = service.findAll();
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void findAllByColorAndSoldTestShouldReturnListWhenRepositoryContainsMatches() {
		
		Collection<EggsTray> eggsTrays = List.of(tray, anotherTraty);
		
		given(repository.findBySoldAndColor(Constants.FALSE, colorRed)).willReturn(List.of(tray, anotherTraty));
		
		List<EggsTray> eggTrayTest = service.findAllByColorAndSold(colorRed, false);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void findAllByColorAndSoldTestShoulReturnEmptyListWhenRepositoryNotContainsMatches() {
		
		Collection<EggsTray> eggsTrays = List.of( );
		
		given(repository.findBySoldAndColor(Constants.FALSE, colorRed)).willReturn((List) eggsTrays);
		
		List<EggsTray> eggTrayTest = service.findAllByColorAndSold(colorRed, false);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	@Test
	void findAllByColorAndSoldTestShoulReturnListWhenRepositoryContainsMatches() {
		
		Collection<EggsTray> eggsTrays = List.of(tray, anotherTraty);
		
		given(repository.findBySoldAndColor(Constants.FALSE, colorRed)).willReturn(List.of(tray, anotherTraty));
		
		List<EggsTray> eggTrayTest = service.findAllByColorAndSold(colorRed, false);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
	
	
	@Test
	void findByColorAndSoldTestShouldReturnEmptyListWhenRepositoryNotContainsMatches() {
		
		Collection<EggsTray> eggsTrays = List.of();
		
		given(repository.findBySoldAndColor(Constants.FALSE, colorRed)).willReturn((List)eggsTrays);
		
		List<EggsTray> eggTrayTest = service.findByColorAndSold(colorRed, false, 2);
			    
	    assertEquals(eggsTrays, eggTrayTest);
	}
}
