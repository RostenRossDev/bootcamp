package com.globant.bootcamp.topic3.model.animal;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Test;
import com.globant.bootcamp.topic3.enums.Color;
import com.globant.bootcamp.topic3.model.FactoryPattern.EggFactory;


public class HenTest {

  Hen henRed = new Hen(Color.RED);

  Hen henRed2 = new Hen(Color.RED);

  Hen henWhite = new Hen(Color.WHITE);

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

  @Test(expected = ClassCastException.class)
  public void equals() {
    assertTrue(henRed.equals(henRed));

    assertTrue(henRed.equals(henRed2));

    assertFalse(henRed.equals(henWhite));

    assertFalse(henRed.equals(egg));
  }


  @Test
  public void getColor() {
    Assert.assertEquals(Color.RED, henRed.getEggsColor());

    Assert.assertNotEquals(Color.WHITE, henRed.getEggsColor());
  }

  @Test
  public void toString_() {

    Assert.assertEquals("bird: Hen, egg colors: RED", henRed.toString());

    Assert.assertNotEquals("bird: Hen, egg colors: WHITE", henRed.toString());

    Assert.assertNotNull(henRed.toString());
  }
}
