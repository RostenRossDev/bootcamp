package com.globant.bootcamp.topic2.interfaces;

import com.globant.bootcamp.topic2.enums.Color;
import com.globant.bootcamp.topic2.model.FactoryPattern.AnimalFactory;

public interface Being<T> {
  T gaveBirth(Color color, AnimalFactory animalFactory);
}
