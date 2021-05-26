package org.o7planning.farmeggmvc.model.animal;

import java.util.ArrayList;
import java.util.List;

import org.o7planning.farmeggmvc.constants.StringConstans;
import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.enums.Gender;
import org.o7planning.farmeggmvc.model.factory.AnimalFactory;
import org.o7planning.farmeggmvc.model.factory.EggFactory;

public class Hen extends Bird {

  private List<Egg> eggs;
  private Color color;

  public Hen(Color color) {

    super(Gender.FEMALE);
    this.eggs = new ArrayList<>();
    this.color = color;
  }

  public Hen() {

    super(null);
  }

  public Color getEggsColor() {

    return this.color;
  }

  public List<Egg> getEgg() {

    return this.eggs;
  }



  public List<Egg> handEgg() {

	  List<Egg> handEggs = this.eggs; // Guardo el arreglo de los huevos de la gallina en una variable
                                // local

    this.eggs = new ArrayList<>();

    return handEggs; // retorno la variable local con los huevos
  }


  public void layEggs(EggFactory eggFactory) {

    this.eggs.add( this.gaveBirth(this.color, eggFactory));

    this.eggs.add( this.gaveBirth(this.color, eggFactory));
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
