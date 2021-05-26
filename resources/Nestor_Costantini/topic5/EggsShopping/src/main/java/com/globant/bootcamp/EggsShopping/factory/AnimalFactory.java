package com.globant.bootcamp.EggsShopping.factory;

import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.animals.Animal;
import com.globant.bootcamp.EggsShopping.models.animals.Bird;

;

public abstract class AnimalFactory {

  public abstract Animal getAnimal(Color color, Bird bird);

  public abstract Animal getAnimal(Color color,com.globant.bootcamp.EggsShopping.enums.Bird bird);
}
