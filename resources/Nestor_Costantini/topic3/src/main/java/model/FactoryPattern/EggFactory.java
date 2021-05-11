package model.FactoryPattern;

import com.globant.bootcamp.topic2.enums.Color;
import model.animal.Animal;
import model.animal.Bird;
import model.animal.Egg;

public class EggFactory extends AnimalFactory {

  @Override
  public Animal<Egg> getAnimal(Color color, Bird bird) {

    if (color != null && bird != null)
      return new Egg(bird, color);

    return new Egg(null, null);
  }


  @Override
  public Animal<Egg> getAnimal(Color color, com.globant.bootcamp.topic2.enums.Bird bird) {

    if (com.globant.bootcamp.topic2.enums.Bird.Hen.equals(bird))
      return new Egg(null, null);

    return null;
  }
}
