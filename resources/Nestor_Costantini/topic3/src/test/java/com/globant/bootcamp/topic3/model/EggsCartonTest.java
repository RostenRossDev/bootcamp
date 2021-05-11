package com.globant.bootcamp.topic3.model;

import org.junit.Assert;
import org.junit.Test;
import com.globant.bootcamp.topic3.enums.Bird;
import com.globant.bootcamp.topic3.enums.Color;
import com.globant.bootcamp.topic3.model.FactoryPattern.EggFactory;
import com.globant.bootcamp.topic3.model.animal.Egg;
import com.globant.bootcamp.topic3.model.animal.Hen;

public class EggsCartonTest {

  EggFactory eggFactory = new EggFactory();

  EggsCarton eggsCarton = new EggsCarton(Color.RED, eggFactory);

  Hen hen = new Hen(Color.RED);

  // Egg egg = new Egg();

  @Test
  public void isFUll() {

    Assert.assertEquals(false, eggsCarton.isFull());
  }

  @Test
  public void getEggs() {

    Assert.assertArrayEquals(EmptyEggs(eggFactory), eggsCarton.getEggs());
  }


  private Egg[][] EmptyEggs(EggFactory eggFactory) {

    Egg[][] eggs = new Egg[5][6];

    for (int i = 0; i < 6; i++) {

      for (int j = 0; j < 5; j++) {
        eggs[j][i] = (Egg) eggFactory.getAnimal(null, Bird.Hen);
      }
    }
    return eggs;
  }
}
