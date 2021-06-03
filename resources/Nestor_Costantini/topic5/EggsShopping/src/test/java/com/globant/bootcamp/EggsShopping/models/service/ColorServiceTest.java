package com.globant.bootcamp.EggsShopping.models.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.globant.bootcamp.EggsShopping.models.Repository.IColorDao;
import com.globant.bootcamp.EggsShopping.models.entity.Color;

class ColorServiceTest {
	
	@Mock
	private IColorDao colorDao;

	@InjectMocks
	private ColorService service;
	
    private AutoCloseable closeable;

	private Color color;
	
	private Optional<Color> colorOp;

	private Color anotherColor;
	
	private Color anotherColor2;
		
    
	@BeforeEach
	void setUp() throws Exception {
		color = new Color();
		anotherColor = new Color();
		anotherColor2 = new Color();
		
		color.setColor("RED");
		color.setEnable(true);
		color.setId(1L);
		
		anotherColor.setColor("RED_STRONG");
		anotherColor.setEnable(true);
		anotherColor.setId(2L);
		
		anotherColor2.setColor("RED");
		anotherColor2.setEnable(true);
		anotherColor2.setId(1L);
		
		colorOp =  Optional.of(color);
		colorOp.orElse(null);
		
		closeable =  MockitoAnnotations.openMocks(this);

	}
	
	@AfterEach
	void setDown() throws Exception {
		closeable.close();
	}


	@Test
	void findAllByColorTest() {
		
		given(colorDao.findAll()).willReturn(List.of(color, anotherColor));
		
		Collection<Color> colors = service.findAllByColor("RED");
			    
	    assertEquals(colors, colors);


	}
	
	@Test
	void findAByColorTest() {
		
		given(colorDao.findByColor("RED")).willReturn(color);
		
		Color colorTest =service.findByColor("RED");
					    
	    assertEquals(color, colorTest);

	}
	

	@Test
	void findByIdTest() {
		
		given(colorDao.findById(1L)).willReturn(colorOp);
		
		Color colorTest =service.findById(1L);
			    
	    assertEquals(colorOp.get(), colorTest);

	}
}
