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

import com.globant.bootcamp.EggsShopping.constants.Constants;
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
	
		
    
	@BeforeEach
	void setUp() throws Exception {
		color = Color.builder().color("RED").enable(Constants.TRUE).id(1L).build();
		anotherColor = Color.builder().color("RED_STRONG").enable(Constants.TRUE).id(2L).build();
			
		colorOp =  Optional.of(color);
		colorOp.orElse(null);
		
		closeable =  MockitoAnnotations.openMocks(this);
	}
	
	@AfterEach
	void setDown() throws Exception {
		closeable.close();
	}


	@Test
	void findAllByColorTestShouldReturnMatchingColorNamesIgnoringCaseWhenRepositoryContainsMatches() {
		
		given(colorDao.findAll()).willReturn(List.of(color, anotherColor));
		
		Collection<Color> colors = service.findAllByColor("RED");
			    
	    assertEquals(colors, colors);


	}
	
	@Test
	void findAllByColorTestShouldReturnEmptyListWhenWRepositoryNotFoundColors() {
		
		given(colorDao.findAll()).willReturn(List.of());
		
		Collection<Color> colors = service.findAllByColor("RED");
			    
	    assertEquals(List.of(), colors);
	}
			
	@Test
	void findAByColorTestSouldReturnColor() {
		
		given(colorDao.findByColor("RED")).willReturn(color);
		
		Color colorTest =service.findByColor("RED");
					    
	    assertEquals(color, colorTest);
	}
	
	@Test
	void findAByColorTestSouldReturnNulWhenRepositoryNotFoundColor() {
		
		given(colorDao.findByColor("RED")).willReturn(null);
		
		Color colorTest =service.findByColor("RED");
					    
	    assertEquals(null, colorTest);
	}
	
	@Test
	void findByIdTestShouldReturnColorById() {
		
		given(colorDao.findById(1L)).willReturn(colorOp);
		
		Color colorTest =service.findById(1L);
			    
	    assertEquals(colorOp.get(), colorTest);

	}
	
	@Test
	void findByIdTestShouldReturnNulWhenRepositoryNotFoundId() {
		
		Optional<Color> op =Optional.ofNullable(null);
	
		given(colorDao.findById(1L)).willReturn(op);
		
		Color colorTest =service.findById(1L);
			    
	    assertEquals(null, colorTest);

	}
}
