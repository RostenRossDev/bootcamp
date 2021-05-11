package com.globant.bootcamp.topic3.model.animal;

import org.junit.Assert;
import org.junit.Test;
import com.globant.bootcamp.topic3.enums.Color;
import com.globant.bootcamp.topic3.enums.Gender;
import com.globant.bootcamp.topic3.model.FactoryPattern.EggFactory;

public class EggTest {
  private final EggFactory eggFactory = new EggFactory();

  private final Bird bird = new Bird(Gender.FEMALE) {

    @Override
    public void makeSound() {
      // TODO Auto-generated method stub

    }
  };
  private final Egg egg = new Egg(bird, Color.RED);

  @Test
  public void getColor() {
    System.out.println("probando el color del guebo");
    Assert.assertEquals(Color.RED, egg.getColor());
  }

  @Test
  public void to_string() {

    Assert.assertEquals("(D)", egg.toString());
  }

  @Test
  public void gaveBirth() {
    Assert.assertEquals(null, egg.gaveBirth(egg.getColor(), eggFactory));
  }
}
