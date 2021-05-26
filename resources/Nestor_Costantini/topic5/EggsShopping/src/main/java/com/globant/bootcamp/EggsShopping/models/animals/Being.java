package com.globant.bootcamp.EggsShopping.models.animals;

import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.factory.AnimalFactory;

public interface Being<T> {
  T gaveBirth(Color color, AnimalFactory animalFactory);
}
