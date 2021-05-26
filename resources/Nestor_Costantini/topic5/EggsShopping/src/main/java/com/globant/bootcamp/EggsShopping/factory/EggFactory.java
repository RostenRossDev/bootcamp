package com.globant.bootcamp.EggsShopping.factory;


import org.springframework.stereotype.Component;

import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.animals.Animal;
import com.globant.bootcamp.EggsShopping.models.animals.Bird;
import com.globant.bootcamp.EggsShopping.models.animals.Egg;

@Component
public class EggFactory extends AnimalFactory {

  @Override
  public Animal<Egg> getAnimal(Color color, Bird bird) {

    if (color != null && bird != null)
      return new Egg(bird, color);

    return new Egg(null, null);
  }


  @Override
  public Animal<Egg> getAnimal(Color color, com.globant.bootcamp.EggsShopping.enums.Bird bird) {

    if (com.globant.bootcamp.EggsShopping.enums.Bird.Hen.equals(bird))
      return new Egg(null, null);

    return null;
  }
}
