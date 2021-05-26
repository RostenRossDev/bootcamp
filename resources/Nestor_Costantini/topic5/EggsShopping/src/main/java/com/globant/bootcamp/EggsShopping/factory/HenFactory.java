package com.globant.bootcamp.EggsShopping.factory;


import org.springframework.stereotype.Component;

import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.animals.Animal;
import com.globant.bootcamp.EggsShopping.models.animals.Bird;
import com.globant.bootcamp.EggsShopping.models.animals.Hen;

@Component
public class HenFactory extends AnimalFactory {

  @Override
  public Animal getAnimal(Color color, Bird bird) {

    if (bird != null)
      return new Hen(color);

    return new Hen();
  }

  @Override
  public Animal getAnimal(Color color, com.globant.bootcamp.EggsShopping.enums.Bird bird) {

    if (bird.Hen.equals(bird) || bird != null)
      return new Hen(color);

    return new Hen();
  }
}
