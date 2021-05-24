package org.o7planning.farmeggmvc.model.factory;

import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.model.animal.Animal;
import org.o7planning.farmeggmvc.model.animal.Bird;

public abstract class AnimalFactory {

  public abstract Animal getAnimal(Color color, Bird bird);

  public abstract Animal getAnimal(Color color, org.o7planning.farmeggmvc.enums.Bird bird);
}
