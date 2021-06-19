package com.globant.bootcamp.EggsShopping.models.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.globant.bootcamp.EggsShopping.constants.StringConstans;

class ColorTest {

	@Mock
	private Color color; 
	private Color anotherSameColor; 
	private Color color2; 
	private Color colorEnableNull; 
	private Color anotherColorEnableNull; 
	private Color colorEnable; 
	private Color colorEnableFalse; 
	private Color AnotherColorEnable; 
	private Color colorId; 
	private Color colorIdNull; 
	private Color anotherColorIdNull; 
	private Color colorAnotherId; 
	private Color colorPrice; 
	private Color colorPriceNull;
	private Color anotherColorPrice; 
	private Color colorCopy; 
	private Color anothercolorCopy; 
	private Color anotherColor; 
	private Egg egg; 
	private EggsPrice prices;
	private AutoCloseable closeable;

	@BeforeEach
	void setUp() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);
		prices = EggsPrice.builder().actual(true).color(color).description("dasdas").id(1L).price(35D).build();
	
		color  = Color.builder().color(StringConstans.RED).enable(Boolean.TRUE).id(1L).build();
		anotherSameColor = Color.builder().color(StringConstans.RED).enable(Boolean.TRUE).id(1L).build();
		color2  = Color.builder().enable(Boolean.TRUE).id(1L).build();
		colorEnableFalse= Color.builder().id(1L).enable(Boolean.FALSE).build();
		colorEnable  = Color.builder().id(1L).enable(Boolean.TRUE).build();		
		colorId =Color.builder().id(1L).enable(Boolean.TRUE).build();
		colorAnotherId =Color.builder().id(2L).enable(Boolean.TRUE).build(); 
		colorIdNull =Color.builder().enable(Boolean.TRUE).build();
		anotherColorIdNull=Color.builder().enable(Boolean.TRUE).build();
		colorPrice = Color.builder().prices(List.of(prices)).id(1L).enable(Boolean.TRUE).build();	
		colorPriceNull = Color.builder().id(1L).enable(Boolean.TRUE).build();
		anotherColorPrice = Color.builder().prices(List.of(prices)).id(1L).enable(Boolean.TRUE).build();	
		AnotherColorEnable = Color.builder().id(1L).enable(Boolean.TRUE).build();
		colorEnableNull  =Color.builder().id(1L).build();
		anotherColorEnableNull=Color.builder().id(1L).build();
		anotherColor= Color.builder().color(StringConstans.WHITE).enable(Boolean.TRUE).id(2L).build();
		colorCopy = color;
		anothercolorCopy =  Color.builder().color(StringConstans.RED).enable(Boolean.TRUE).id(1L).build();
		egg = Egg.builder().build();
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();

	}

	@Test
	void colorTestShouldReturnTrueWhenCompareWhitSelf() {
		Assertions.assertEquals(color, color);
	}
	
	@Test
	void colorTestShouldByEqualWhenCompareWhitAnotherReferenceToTheSameColor() {
		Assertions.assertEquals(colorCopy, color);
	}
	
	@Test
	void colorTestShouldReturnFalseWhenCompareWhitNull() {
		Assertions.assertFalse(color.equals(null));
	}
	
	@Test
	void colorTestShouldReturnTrueWhenCompareWhitAnotherReferenceToTheSameColor() {
		Assertions.assertTrue(color.equals(colorCopy));
	}
	
	@Test
	void colorTestShouldReturnFalseWhenCompareWhitAnotherColor() {
		Assertions.assertFalse(color.equals(anotherColor));
	}
	
	@Test
	void colorTestShouldReturnFalseWhenCompareThisWhitAnotherColorCopy() {
		Assertions.assertTrue(anothercolorCopy.equals(color));
	}
	
	@Test
	void colorTestShouldReturnFalseWhenCompareWhitAnotherObjectClass() {
		Assertions.assertFalse(color.equals(egg));
	}
	
	@Test
	void colorTestShouldReturnFalseWhenCompareThisWhioutColorWhitAnotherColor() {
		Assertions.assertFalse(color2.equals(color));
	}
	
	//enabled	
	@Test
	void colorTestShouldReturnFalseWhenCompareThisWhioutEnabledWhitAnotherColor() {
		Assertions.assertFalse(colorEnableNull.equals(colorEnable));
	}
	
	@Test
	void colorTestShouldReturnTrueWhenCompareThisEnabledWhitAnotherColor() {
		Assertions.assertTrue(colorEnableNull.equals(anotherColorEnableNull));
	}
	
	@Test
	void colorTestShouldReturnTrueWhenCompareThisEnabledWhitAnotherColorEnabled() {
		Assertions.assertTrue(colorEnable.equals(AnotherColorEnable));
	}
	
	@Test
	void colorTestShouldReturnFalseWhenCompareThisEnabledWhitAnotherColorNotEnabled() {
		Assertions.assertFalse(colorEnable.equals(colorEnableFalse));
	}
	
	//id
	@Test
	void colorTestShouldReturnFalseWhenCompareThisIdWhitAnotherColorIdNull() {
		Assertions.assertFalse(colorId.equals(colorAnotherId));
	}
	
	
	@Test
	void colorTestShouldReturnFalseWhenCompareThisIdWhitAnotherColorIdNotNul() {
		Assertions.assertFalse(colorId.equals(colorIdNull));
	}
	@Test
	void colorTestShouldReturnTrueWhenCompareThisidWhitAnotherSameColorId() {
		Assertions.assertTrue(colorIdNull.equals(anotherColorIdNull));
	}
	
	@Test
	void colorTestShouldReturnFalseWhenCompareThisNullIdWhitColorId() {
		Assertions.assertFalse(colorIdNull.equals(colorId));
	}
	// Price
	
	@Test
	void colorTestShouldReturnFalseWhenCompareThisPriceWhitAnotherColorPriceNull() {
		Assertions.assertFalse(colorPrice.equals(colorAnotherId));
	}
	
	
	@Test
	void colorTestShouldReturnFalseWhenCompareThisPriceWhitAnotherColorPriceNotNul() {
		Assertions.assertFalse(colorPrice.equals(colorPriceNull));
	}
	@Test
	void colorTestShouldReturnTrueWhenCompareThisPriceWhitAnotherSameColorPrice() {
		Assertions.assertTrue(colorPrice.equals(anotherColorPrice));
	}
	
	@Test
	void colorTestShouldReturnFalseWhenCompareThisNullPriceWhitColorPriced() {
		Assertions.assertFalse(colorPriceNull.equals(colorPrice));
	}

}
