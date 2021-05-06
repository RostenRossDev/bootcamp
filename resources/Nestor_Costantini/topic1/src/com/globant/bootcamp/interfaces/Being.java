package com.globant.bootcamp.interfaces;

import com.globant.bootcamp.enums.Color;
import com.globant.bootcamp.model.FactoryPattern.AnimalFactory;

public interface Being<T> {
    T gaveBirth(Color color, AnimalFactory animalFactory);
}
