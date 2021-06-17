package com.globant.bootcamp.EggsShopping.models.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collection;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.constants.StringConstans;
import com.globant.bootcamp.EggsShopping.models.Repository.IEggPriceDao;
import com.globant.bootcamp.EggsShopping.models.entity.Color;
import com.globant.bootcamp.EggsShopping.models.entity.EggsPrice;

class PriceEggServiceTest {
	
	private final Log LOG = LogFactory.getLog(this.getClass());	

	@Mock
	private IEggPriceDao repository;

	@InjectMocks
	private PriceEggService service;

	private AutoCloseable closeable;

	private Color color;
	
	private EggsPrice price;
	
	private EggsPrice oldPrice;
	
	private EggsPrice oldPriceDisabled;
	
	private EggsPrice anotherPrice;
		
	private EggsPrice updatePrice;
	
	private EggsPrice persistPrice;
	
	@BeforeEach
	void setUp() throws Exception {
		closeable =  MockitoAnnotations.openMocks(this);
		
		color = Color.builder().color(StringConstans.RED).id(1L).enable(Constants.TRUE).build();
				
		price = EggsPrice.builder().actual(Constants.TRUE).color(color).description("Some Text").price(35D).build();
		
		updatePrice = EggsPrice.builder().actual(Constants.TRUE).color(color).description("Some Text").price(40D).build();
		
		persistPrice = EggsPrice.builder().actual(Constants.TRUE).color(color).description("Some Text").id(2L).price(40D).build();
		
		oldPrice = EggsPrice.builder().actual(Constants.FALSE).color(color).description("Some Text").id(1L).price(35D).build();
		
		anotherPrice = EggsPrice.builder().actual(Constants.TRUE).color(color).description("Some Text").id(3L).price(35D).build();
	
		oldPriceDisabled = EggsPrice.builder().actual(Constants.FALSE).color(color).description("Some Text").id(3L).price(35D).build();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void priceByColorTestShouldReturnDoubleWhenRepositoryContainsMatches() {

		given(repository.findByColorAndActual(color, true)).willReturn(price);
		
		Double priceD = service.priceByColor(color);
			    
	    assertEquals(35D, priceD);
	}
	
	@Test
	void priceByColorTestShouldReturnNullWhenRepositoryNotContainsMatches() {

		given(repository.findByColorAndActual(color, true)).willReturn(null);
		
		Double priceD = service.priceByColor(color);
			    
	    assertEquals(null, priceD);
	}
	
	@Test
	void updatePriceTestShouldReturnPriceWhenRepositoryPersist() {
			
		//given(repository.disableColor(false, color, true)).willReturn(oldPriceDisabled);
										
		given(repository.save(oldPrice)).willReturn(oldPriceDisabled);
		
		given(repository.save(updatePrice)).willReturn(persistPrice);
		
		EggsPrice priceTest = service.updatePrice(updatePrice,oldPrice);
					    
	    assertEquals(persistPrice, priceTest);
	}
	
	@Test
	void updatePriceTestShouldThrowPersistenceExceptionWhenSaveOldPriceRepositoryFail() throws PersistenceException{
		
		given(repository.findByColorAndActual(color, true)).willReturn(price);
		
		given(repository.save(price)).willThrow(new PersistenceException("The expected message"));
		
		given(repository.save(updatePrice)).willReturn(updatePrice);		    
	}
	
	@Test
	void updatePriceTestShouldThrowPersistenceExceptionWhenSaveNewPriceRepositoryFail() throws PersistenceException{
		
		given(repository.findByColorAndActual(color, true)).willReturn(price);
		
		given(repository.save(price)).willReturn(price);
		
		given(repository.save(updatePrice)).willThrow(new PersistenceException("The expected message"));			    
	}

	@Test
	void deletePriceByIdTestShouldThrowPersistenceExceptionWhenDeletePriceRepositoryFail(){
		
	        // perform the call
		 	service.deletePriceById(price.getId());

	        // verify the mocks
	        verify(repository, times(1)).deleteById(price.getId());
    }
	
	@Test
	void allEggsPricesTestShouldReturnListWhenRepositoryContainsMatches(){
		
		Collection<EggsPrice> prices = List.of(price, anotherPrice);
		
		given(repository.findAll()).willReturn((List)prices);
				
		List<EggsPrice> priceTest = service.allEggsPrices();
	    
	    assertEquals(prices, priceTest);
	}
	
	@Test
	void allEggsPricesTestShouldReturnEmptyListWhenRepositoryNotContainsMatches(){
		
		Collection<EggsPrice> prices = List.of();
		
		given(repository.findAll()).willReturn((List)prices);
				
		List<EggsPrice> priceTest = service.allEggsPrices();
	    
	    assertEquals(prices, priceTest);
	}
	

	@Test
	void allEggsByColorPricesTestShouldReturnListWhenRepositoryContainsMatches(){
		
		Collection<EggsPrice> prices = List.of(price, anotherPrice);
		
		given(repository.findByColor(color)).willReturn((List)prices);
				
		List<EggsPrice> priceTest = service.allEggsPriceByColor(color);
	    
	    assertEquals(prices, priceTest);
	}
	
	@Test
	void allEggsPriceByColorTestShouldReturnEmptyListWhenRepositoryContainsMatches(){
		
		Collection<EggsPrice> prices = List.of();
		
		given(repository.findByColor(color)).willReturn((List)prices);
				
		List<EggsPrice> priceTest = service.allEggsPrices();
	    
	    assertEquals(prices, priceTest);
	}
	
}
