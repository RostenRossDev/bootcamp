package com.globant.bootcamp.topic3.model.animal;


import org.junit.Assert;
import org.junit.Test;
import com.globant.bootcamp.topic3.enums.Color;
import com.globant.bootcamp.topic3.model.FactoryPattern.EggFactory;


public class HenTest {

  Hen henRed = new Hen(Color.RED);

  Egg egg = new Egg((Bird) henRed, henRed.getEggsColor());

  EggFactory eggFActory = new EggFactory();

  Egg[] eggs = {egg, egg};

  @Test
  public void getColorEggs() {

    Assert.assertEquals(Color.RED, henRed.getEggsColor());
  }

  @Test
  public void gaveBirth() {

    Assert.assertEquals(egg, henRed.gaveBirth(henRed.getEggsColor(), eggFActory));
  }

  @Test
  public void handEggs() {

    henRed.layEggs(eggFActory);
    Assert.assertArrayEquals(eggs, henRed.handEgg());
  }

  @Test
  public void getEggs() {
    henRed.layEggs(eggFActory);
    Assert.assertArrayEquals(eggs, henRed.getEgg());
  }
}
