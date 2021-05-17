package com.globant.bootcamp.topic3.model.animal;

import com.globant.bootcamp.topic3.enums.Color;
import com.globant.bootcamp.topic3.enums.Gender;
import com.globant.bootcamp.topic3.model.FactoryPattern.AnimalFactory;
import com.globant.bootcamp.topic3.model.FactoryPattern.EggFactory;

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
