package com.globant.bootcamp.topic3.model.animal;


import com.globant.bootcamp.topic3.enums.Color;
import com.globant.bootcamp.topic3.enums.Gender;
import com.globant.bootcamp.topic3.interfaces.Being;
import com.globant.bootcamp.topic3.model.FactoryPattern.AnimalFactory;

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
