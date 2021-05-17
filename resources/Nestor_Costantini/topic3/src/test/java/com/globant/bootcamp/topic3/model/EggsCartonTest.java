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

  EggsCarton eggsCartonWhite = new EggsCarton(Color.WHITE, eggFactory);

  EggsCarton eggsCartonRedHalf = new EggsCarton(Color.RED, eggFactory);

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

  @Test
  public void getColor() {
    Assert.assertEquals(Color.RED, eggsCarton.getColor());

    Assert.assertNotEquals(Color.WHITE, eggsCarton.getColor());

    Assert.assertNotNull(eggsCarton.getColor());
  }

  @Test
  public void equals() {
    eggsCartonRedHalf.addEgg(new Egg());

    Assert.assertEquals(eggsCartonRedHalf, eggsCarton);

    Assert.assertEquals(eggsCarton, eggsCarton);

    Assert.assertNotEquals(eggsCarton, eggsCartonWhite);

    Assert.assertNotNull(eggsCarton);
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
