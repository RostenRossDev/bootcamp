package model.FactoryPattern;


import org.junit.Assert;
import org.junit.Test;

import com.globant.bootcamp.topic2.enums.Bird;
import com.globant.bootcamp.topic2.enums.Color;

import model.animal.Hen;

public class HenFactoryTest {
	
	Hen emptyHen = new Hen();
	
	Hen henRed = new Hen(Color.RED);
	
	HenFactory henFactory = new HenFactory();
	
	@Test
	public void getAnimal() {
		
		Assert.assertEquals(henRed, henFactory.getAnimal(Color.RED, henRed));
	}
	
	
	public void getAnimalByEnum() {
		
		Assert.assertEquals(henRed, henFactory.getAnimal(Color.RED, Bird.Hen));
	}
}
