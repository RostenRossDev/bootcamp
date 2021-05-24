package org.o7planning.farmeggmvc.model.factory;

import org.o7planning.farmeggmvc.enums.Bird;
import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.model.animal.Animal;
import org.o7planning.farmeggmvc.model.animal.Hen;
import org.springframework.stereotype.Component;

@Component
public class HenFactory extends AnimalFactory {

  @Override
  public Animal getAnimal(Color color, org.o7planning.farmeggmvc.model.animal.Bird bird) {

    if (bird != null)
      return new Hen(color);

    return new Hen();
  }

  @Override
  public Animal getAnimal(Color color, Bird bird) {

    if (bird.Hen.equals(bird) || bird != null)
      return new Hen(color);

    return new Hen();
  }
}
