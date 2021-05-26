package com.globant.bootcamp.EggsShopping.models.animals;

import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.enums.Gender;
import com.globant.bootcamp.EggsShopping.factory.AnimalFactory;
import com.globant.bootcamp.EggsShopping.factory.EggFactory;

public abstract class Bird extends Animal<Egg> {

  public Bird(Gender gender) {
    super(gender);
  }

  @Override
  public Egg gaveBirth(Color color, AnimalFactory animalFactory) {
    EggFactory eggFactory = (EggFactory) animalFactory;
    if (this.gender == Gender.FEMALE) {
      Hen mother = new Hen(color);
      return (Egg) eggFactory.getAnimal(color, mother);
    }
    return null;
  }
}
