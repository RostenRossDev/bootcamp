package com.globant.bootcamp.topic3.model.animal;

import com.globant.bootcamp.topic3.constants.StringConstans;
import com.globant.bootcamp.topic3.enums.Color;
import com.globant.bootcamp.topic3.model.FactoryPattern.AnimalFactory;

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
      if (this.toString().equals(egg.toString())
          || this.getColor().equals(egg.getColor()) && this.getBaby().equals(egg.getBaby())) {
        return true;
      }
    }

    return false;
  }


}
