package org.o7planning.farmeggmvc.model.animal;

import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.enums.Gender;
import org.o7planning.farmeggmvc.model.factory.AnimalFactory;
import org.o7planning.farmeggmvc.model.factory.EggFactory;

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
