package com.globant.bootcamp.topic3.interfaces;

import com.globant.bootcamp.topic3.enums.Color;
import com.globant.bootcamp.topic3.model.FactoryPattern.AnimalFactory;

public interface Being<T> {
  T gaveBirth(Color color, AnimalFactory animalFactory);
}
