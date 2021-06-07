package com.globant.bootcamp.EggsShopping.models.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import javax.persistence.PersistenceException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.constants.StringConstans;
import com.globant.bootcamp.EggsShopping.models.Repository.IColorDao;
import com.globant.bootcamp.EggsShopping.models.Repository.IEggDao;
import com.globant.bootcamp.EggsShopping.models.entity.Color;
import com.globant.bootcamp.EggsShopping.models.entity.Egg;

class EggServiceTest {
	@Mock
	private IEggDao repository;

	@InjectMocks
	private EggService service;
	
    private AutoCloseable closeable;
    
	private Egg egg;
	
	private Color color;
	
	@BeforeEach
	void setUp() throws Exception {
		closeable =  MockitoAnnotations.openMocks(this);

		color = Color.builder().id(1L).color(StringConstans.RED).enable(Constants.TRUE).build();
	
		egg = Egg.builder().id(1L).color(color).build();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void eggSaveTestSouldReturnEgg() {
		
		given(repository.save(egg)).willReturn(egg);
		
		Egg eggTest = service.save(egg);
			    
	    assertEquals(egg, eggTest);
	}
	
	@Test
	void eggSaveTestSouldReturnNull() throws PersistenceException{
		
		given(repository.save(egg)).willThrow(new PersistenceException("The expected message"));			    

	}

}
