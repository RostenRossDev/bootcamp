package com.globant.bootcamp.topic3.model;

import org.junit.Assert;
import org.junit.Test;
import com.globant.bootcamp.topic3.enums.Color;
import com.globant.bootcamp.topic3.model.FactoryPattern.EggFactory;

public class FarmerTest {
  EggsCarton[] eggsCartons = new EggsCarton[3];

  EggFactory eggFActory = new EggFactory();

  Farmer farmer = new Farmer(eggFActory);

  @Test
  public void getEggsCartons() {

    initEggsCartons(eggFActory);
    Assert.assertArrayEquals(eggsCartons, farmer.getEggsCartons());
  }

  private void initEggsCartons(EggFactory eggFactory) {


    this.eggsCartons[0] = new EggsCarton(Color.RED, eggFactory);

    this.eggsCartons[1] = new EggsCarton(Color.RED, eggFactory);

    this.eggsCartons[2] = new EggsCarton(Color.WHITE, eggFactory);
  }

}
