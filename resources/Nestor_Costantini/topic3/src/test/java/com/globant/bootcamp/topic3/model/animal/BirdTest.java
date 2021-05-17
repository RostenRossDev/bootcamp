package com.globant.bootcamp.topic3.model.animal;

import org.junit.Assert;
import org.junit.Test;
import com.globant.bootcamp.topic3.enums.Color;
import com.globant.bootcamp.topic3.enums.Gender;
import com.globant.bootcamp.topic3.model.FactoryPattern.AnimalFactory;
import com.globant.bootcamp.topic3.model.FactoryPattern.EggFactory;

public class BirdTest {

  private AnimalFactory eggFactory = new EggFactory();

  private Bird birdFemale = new Bird(Gender.FEMALE) {

    @Override
    public void makeSound() {
      // TODO Auto-generated method stub

    }
  };

  private Bird birdMale = new Bird(Gender.MALE) {

    @Override
    public void makeSound() {
      // TODO Auto-generated method stub

    }
  };
  private Egg eggRed = new Egg(birdFemale, Color.RED);

  @Test
  public void gaveBirth() {

    Assert.assertEquals(eggRed, birdFemale.gaveBirth(Color.RED, eggFactory));

    Assert.assertNotEquals(eggRed, birdFemale.gaveBirth(Color.WHITE, eggFactory));

    Assert.assertNotNull(birdFemale.gaveBirth(Color.RED, eggFactory));

    Assert.assertNull(birdMale.gaveBirth(Color.WHITE, eggFactory));

  }

}
