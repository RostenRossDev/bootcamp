package com.globant.bootcamp.topic2.model.FactoryPattern;



import com.globant.bootcamp.topic2.enums.Bird;
import com.globant.bootcamp.topic2.enums.Color;
import com.globant.bootcamp.topic2.model.animal.Animal;
import com.globant.bootcamp.topic2.model.animal.Hen;;

public class HenFactory extends AnimalFactory {

  @Override
  public Animal getAnimal(Color color, com.globant.bootcamp.topic2.model.animal.Bird bird) {

    if (bird != null)
      return new Hen(color);

    return new Hen();
  }

  @Override
  public Animal getAnimal(Color color, Bird bird) {

    if (bird.Hen.equals(bird))
      return new Hen(color);

    return new Hen();
  }
}
