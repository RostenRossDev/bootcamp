package org.o7planning.farmeggmvc.model.factory;

import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.model.animal.Animal;
import org.o7planning.farmeggmvc.model.animal.Bird;
import org.o7planning.farmeggmvc.model.animal.Egg;
import org.springframework.stereotype.Component;

@Component
public class EggFactory extends AnimalFactory {

  @Override
  public Animal<Egg> getAnimal(Color color, Bird bird) {

    if (color != null && bird != null)
      return new Egg(bird, color);

    return new Egg(null, null);
  }


  @Override
  public Animal<Egg> getAnimal(Color color, org.o7planning.farmeggmvc.enums.Bird bird) {

    if (org.o7planning.farmeggmvc.enums.Bird.Hen.equals(bird))
      return new Egg(null, null);

    return null;
  }
}
