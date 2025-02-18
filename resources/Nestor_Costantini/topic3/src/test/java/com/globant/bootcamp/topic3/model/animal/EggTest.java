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

  private final Bird birdFemale = new Bird(Gender.FEMALE) {

    @Override
    public void makeSound() {
      // TODO Auto-generated method stub

    }
  };

  private final Bird birdMale = new Bird(Gender.MALE) {

    @Override
    public void makeSound() {
      // TODO Auto-generated method stub

    }
  };

  private final Egg eggRed = new Egg(birdFemale, Color.RED);

  private final Egg eggRed2 = new Egg(birdFemale, Color.RED);

  private final Egg eggWhite = new Egg(birdFemale, Color.WHITE);

  private final Egg eggNUll = new Egg(null, null);


  @Test
  public void getColor() {

    Assert.assertEquals(Color.RED, eggRed.getColor());
  }

  @Test
  public void to_string() {


    Assert.assertEquals("(D)", eggRed.toString());

    Assert.assertEquals("(O)", eggWhite.toString());

    Assert.assertEquals("(G)", eggNUll.toString());

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

    assertFalse(eggRed.equals(birdFemale));

    assertTrue(eggRed.getBaby().equals(birdFemale));

    assertFalse(eggRed.getBaby().equals(birdMale));
  }

  @Test
  public void getBaby() {

    Assert.assertEquals(birdFemale, eggRed.getBaby());

    Assert.assertNotNull(eggRed.getBaby());

    Assert.assertNull(eggNUll.getBaby());

    Assert.assertEquals(birdFemale, eggRed.getBaby());
  }

}
