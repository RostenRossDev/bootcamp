package model.animal;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.globant.bootcamp.topic2.constants.StringConstans;
import com.globant.bootcamp.topic2.enums.Color;
import com.globant.bootcamp.topic2.enums.Gender;

import model.FactoryPattern.EggFactory;

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
