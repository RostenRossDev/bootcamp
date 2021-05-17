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

  Egg egg2 = new Egg((Bird) henWhite, henWhite.getEggsColor());

  Egg egg3 = new Egg(null, null);

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
  public void toStringHen() {

    Assert.assertEquals("(D)", egg.toString());

    Assert.assertEquals("(O)", egg2.toString());

    Assert.assertEquals("(G)", egg3.toString());
  }
}
