package com.globant.bootcamp.topic3.model.animal;

import com.globant.bootcamp.topic3.constants.StringConstans;
import com.globant.bootcamp.topic3.enums.Color;
import com.globant.bootcamp.topic3.enums.Gender;
import com.globant.bootcamp.topic3.model.FactoryPattern.AnimalFactory;
import com.globant.bootcamp.topic3.model.FactoryPattern.EggFactory;

public class Hen extends Bird {

  private Egg[] eggs;
  private Color color;

  public Hen(Color color) {

    super(Gender.FEMALE);
    this.eggs = new Egg[2];
    this.color = color;
  }

  public Hen() {

    super(null);
  }

  public Color getEggsColor() {

    return this.color;
  }

  public Egg[] getEgg() {

    return this.eggs;
  }



  public Egg[] handEgg() {

    Egg[] handEggs = this.eggs; // Guardo el arreglo de los huevos de la gallina en una variable
                                // local

    this.eggs = new Egg[2];

    return handEggs; // retorno la variable local con los huevos
  }


  public void layEggs(EggFactory eggFactory) {

    this.eggs[0] = this.gaveBirth(this.color, eggFactory);

    this.eggs[1] = this.gaveBirth(this.color, eggFactory);
  }


  @Override
  public void makeSound() {

    System.out.println(StringConstans.KAKAREO);
  }

  @Override
  public Egg gaveBirth(Color color, AnimalFactory AnimalFactory) {

    EggFactory eggFactory = (EggFactory) AnimalFactory;

    return (Egg) eggFactory.getAnimal(color, this);
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return "bird: Hen, egg colors: " + this.color;
  }

  @Override
  public boolean equals(Object obj) {
    // TODO Auto-generated method stub
    Hen hen = (Hen) obj;
    if (obj instanceof Hen) {
      if (this.color.equals(hen.getEggsColor())) {
        return true;
      }
    }
    return false;
  }



}
