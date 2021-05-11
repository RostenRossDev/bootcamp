package model.FactoryPattern;


import org.junit.Assert;
import org.junit.Test;
import com.globant.bootcamp.topic2.enums.Bird;
import com.globant.bootcamp.topic2.enums.Color;
import model.animal.Egg;
import model.animal.Hen;

public class EggFactoryTest {
  Hen hen = new Hen(Color.RED);

  Egg egg = new Egg(hen, Color.RED);

  EggFactory eggFActory = new EggFactory();

  @Test
  public void getAnimal() {

    Assert.assertEquals(egg, eggFActory.getAnimal(Color.RED, hen));
  }

  public void getAnimalByEnum() {

    Assert.assertEquals(egg, eggFActory.getAnimal(Color.RED, Bird.Hen));
  }
}
