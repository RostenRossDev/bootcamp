package com.globant.bootcamp.model.FactoryPattern;

import com.globant.bootcamp.enums.Color;
import com.globant.bootcamp.model.animal.Animal;
import com.globant.bootcamp.model.animal.Bird;
import com.globant.bootcamp.model.animal.Egg;

public class EggFactory extends AnimalFactory {
  @Override
  public Animal getAnimal(Color color, Bird bird) {

    if (color != null && bird != null) {
      Egg egg = new Egg(bird, color);
      return egg;
    }

    return new Egg(null, null);
  }

  @Override
  public Animal getAnimal(Color color, com.globant.bootcamp.enums.Bird bird) {

    if (com.globant.bootcamp.enums.Bird.Hen.equals(bird))
      return new Egg(null, null);

    return null;
  }
}
