package com.globant.bootcamp.topic2.model.FactoryPattern;

import com.globant.bootcamp.topic2.enums.Color;
import com.globant.bootcamp.topic2.model.animal.Animal;
import com.globant.bootcamp.topic2.model.animal.Bird;

public abstract class AnimalFactory {

  public abstract Animal getAnimal(Color color, Bird bird);

  public abstract Animal getAnimal(Color color, com.globant.bootcamp.topic2.enums.Bird bird);
}
