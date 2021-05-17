package com.globant.bootcamp.topic3.model.animal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
  private final Egg eggRed = new Egg(bird, Color.RED);

  private final Egg eggRed2 = new Egg(bird, Color.RED);

  private final Egg eggWhite = new Egg(bird, Color.WHITE);

  private final Egg eggNUll = new Egg(null, null);


  @Test
  public void getColor() {

    Assert.assertEquals(Color.RED, eggRed.getColor());
  }

  @Test
  public void to_string() {

    Assert.assertEquals("(D)", eggRed.toString());
  }

  @Test
  public void gaveBirth() {
    Assert.assertEquals(null, eggRed.gaveBirth(eggRed.getColor(), eggFactory));
  }

  @Test(expected = ClassCastException.class)
  public void equals() {

    assertTrue(eggRed.equals(eggRed));

    assertTrue(eggRed.equals(eggRed2));

    assertFalse(eggRed.equals(eggWhite));

    assertFalse(eggRed.equals(eggNUll));

    assertFalse(eggRed.equals(bird));
  }

  @Test
  public void getBaby() {

    Assert.assertEquals(bird, eggRed.getBaby());

    Assert.assertNotNull(eggRed.getBaby());

    Assert.assertNull(eggNUll.getBaby());

    Assert.assertEquals(bird, eggRed.getBaby());
  }

}
