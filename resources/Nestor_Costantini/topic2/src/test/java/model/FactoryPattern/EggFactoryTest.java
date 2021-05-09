package model.FactoryPattern;

import static org.junit.Assert.*;

import org.junit.Test;

import com.globant.bootcamp.topic2.enums.Color;

import model.animal.Egg;
import model.animal.Hen;

public class EggFactoryTest {
	Hen hen = new Hen(Color.RED);
	
	Egg egg = new Egg(Color.RED, hen);
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
