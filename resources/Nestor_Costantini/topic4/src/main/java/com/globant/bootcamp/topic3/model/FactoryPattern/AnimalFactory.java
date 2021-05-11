package com.globant.bootcamp.topic3.model.FactoryPattern;

import com.globant.bootcamp.topic3.enums.Color;
import com.globant.bootcamp.topic3.model.animal.Animal;
import com.globant.bootcamp.topic3.model.animal.Bird;

public abstract class AnimalFactory {

  public abstract Animal getAnimal(Color color, Bird bird);

  public abstract Animal getAnimal(Color color, com.globant.bootcamp.topic3.enums.Bird bird);
}
