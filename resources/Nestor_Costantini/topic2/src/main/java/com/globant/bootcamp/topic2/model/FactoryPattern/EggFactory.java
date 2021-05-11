package com.globant.bootcamp.topic2.model.FactoryPattern;

import com.globant.bootcamp.topic2.enums.Color;
import com.globant.bootcamp.topic2.model.animal.Animal;
import com.globant.bootcamp.topic2.model.animal.Bird;
import com.globant.bootcamp.topic2.model.animal.Egg;

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
