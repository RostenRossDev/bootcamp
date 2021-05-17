package com.globant.bootcamp.topic3.model.FactoryPattern;


import org.junit.Assert;
import org.junit.Test;
import com.globant.bootcamp.topic3.enums.Color;
import com.globant.bootcamp.topic3.model.animal.Egg;
import com.globant.bootcamp.topic3.model.animal.Hen;

public class EggFactoryTest {
  Hen hen = new Hen(Color.RED);

  Egg egg = new Egg(hen, Color.RED);

  Egg eggNUll = new Egg(null, null);

  EggFactory eggFActory = new EggFactory();

  @Test
  public void getAnimal() {

    Assert.assertEquals(egg, eggFActory.getAnimal(Color.RED, hen));

  }

}
