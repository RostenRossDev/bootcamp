package org.o7planning.farmeggmvc.model.animal;

import org.o7planning.farmeggmvc.constants.StringConstans;
import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.model.factory.AnimalFactory;

public class Egg extends Animal<Egg> {

  private Color color;

  private Bird baby;

  public Egg(Bird bird, Color color) {

    super(randomGender());

    this.baby = bird;

    this.color = color;
  }

  public Egg() {

    super(null);
  }

  public Color getColor() {

    return this.color;
  }

  public Animal<Bird> getBaby() {
    return (Animal) this.baby;
  }

  @Override
  public String toString() {

    String eggStr = StringConstans.EMPTY_EGG;

    if (Color.RED.equals(this.color)) {

      eggStr = StringConstans.RED_EGG;
    } else if (Color.WHITE.equals(this.color)) {

      eggStr = StringConstans.WHITE_EGG;
    }

    return eggStr;
  }

  @Override
  public void makeSound() {

    System.out.println(StringConstans.EGG_SOUND);
  }

  @Override
  public Egg gaveBirth(Color color, AnimalFactory animalFactory) {

    return null;
  }

  @Override
  public boolean equals(Object obj) {
    // TODO Auto-generated method stub
    Egg egg = (Egg) obj;
    if (obj instanceof Egg) {
      if (this.getColor() != null && egg.getColor() != null) {

        if (this.getColor().equals(egg.getColor())) {
          return true;
        }
      } else {
        return true;
      }
    }

    return false;
  }
}
