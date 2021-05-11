package com.globant.bootcamp.topic3.model.FactoryPattern;


import org.junit.Assert;
import org.junit.Test;
import com.globant.bootcamp.topic3.enums.Bird;
import com.globant.bootcamp.topic3.enums.Color;
import com.globant.bootcamp.topic3.model.animal.Hen;

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
