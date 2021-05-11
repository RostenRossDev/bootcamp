package model.FactoryPattern;

import com.globant.bootcamp.topic2.enums.Color;
import model.animal.Animal;
import model.animal.Bird;

public abstract class AnimalFactory {

  public abstract Animal getAnimal(Color color, Bird bird);

  public abstract Animal getAnimal(Color color, com.globant.bootcamp.topic2.enums.Bird bird);
}
