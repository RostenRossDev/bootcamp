package com.globant.bootcamp.topic2.interfaces;

import com.globant.bootcamp.topic2.enums.Color;
import model.FactoryPattern.AnimalFactory;

public interface Being<T> {
    T gaveBirth(Color color, AnimalFactory animalFactory);
}
