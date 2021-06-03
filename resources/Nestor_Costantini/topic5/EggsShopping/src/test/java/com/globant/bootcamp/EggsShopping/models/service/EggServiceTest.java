package com.globant.bootcamp.EggsShopping.models.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

		color = new Color();
		color.setId(1L);
		color.setColor("RED");
		color.setEnable(true);
		
		egg = new Egg();
		egg.setId(1L);
		egg.setColor(color);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void saveTest() {
		given(repository.save(egg)).willReturn(egg);
		
		Egg eggTest = service.save(egg);
			    
	    assertEquals(egg, eggTest);
	}

}
