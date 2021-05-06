package com.globant.bootcamp.model.FactoryPattern;

import com.globant.bootcamp.enums.Color;
import com.globant.bootcamp.model.animal.Animal;
import com.globant.bootcamp.model.animal.Bird;

public abstract class AnimalFactory {

    public abstract Animal getAnimal(Color color, Bird bird);

    public abstract Animal getAnimal(Color color, Enum bird);
}
