package org.o7planning.farmeggmvc.model.animal;

import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.enums.Gender;
import org.o7planning.farmeggmvc.interfaces.Being;
import org.o7planning.farmeggmvc.model.factory.AnimalFactory;

public abstract class Animal<T> implements Being<T> {
  protected Gender gender;

  public Animal(Gender gender) {
    this.gender = gender;
  }

  public abstract void makeSound();

  protected static Gender randomGender() {
    return (Math.random() < 0.5 ? Gender.FEMALE : Gender.MALE);
  }

  public abstract T gaveBirth(Color color, AnimalFactory animalFactory);
}
