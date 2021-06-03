package com.globant.bootcamp.topic3;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import com.globant.bootcamp.topic3.enums.Color;
import com.globant.bootcamp.topic3.model.EggsCarton;
import com.globant.bootcamp.topic3.model.Farmer;
import com.globant.bootcamp.topic3.model.FactoryPattern.EggFactory;
import com.globant.bootcamp.topic3.model.FactoryPattern.HenFactory;
import com.globant.bootcamp.topic3.model.animal.Bird;
import com.globant.bootcamp.topic3.model.animal.Hen;


public class App {
  public static Logger logg = Logger.getLogger(App.class);

  public static void main(String[] args) {
    BasicConfigurator.configure(); 
    logg.info("This is Logger Info");

    EggFactory eggFactory = new EggFactory(); // Create an egg factory.

    HenFactory henFactory = new HenFactory(); // Create a hen Factory.

    Hen[] henHouse = initHenHouse(henFactory); // Create a hen house (array of Hen).

    layEggs(henHouse, eggFactory); // Hens lay eggs.

    Farmer farmer = new Farmer(eggFactory); // Create a farmer.

    farmer.startFarming(henHouse); // farmer farming all eggs of hen house.

    showEggsCartons(farmer); // Show eggs carton content.
  }



  public static Hen[] initHenHouse(HenFactory henFactory) {
    Hen[] hens = new Hen[40]; // init the hen array.

    for (int i = 0; i < 40; i++) {
      Bird hen = (Bird) henFactory.getAnimal(null, com.globant.bootcamp.topic3.enums.Bird.Hen);
      if (i < 12) { // decide what color of eggs lays the hen.
        hens[i] = (Hen) henFactory.getAnimal(Color.WHITE, hen);
      } else {
        hens[i] = (Hen) henFactory.getAnimal(Color.RED, hen);
      }
    }
    return hens;
  }

  public static void layEggs(Hen[] hens, EggFactory eggFactory) {

    for (Hen hen : hens) {
      hen.layEggs(eggFactory); // each hen lay eggs.
    }
  }

  public static void showEggsCartons(Farmer farmer) {

    for (EggsCarton eggsCartons : farmer.getEggsCartons()) {

      System.out.println("================\n"); // print a separator of eggs carton.
      for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 5; j++) {
          System.out.print(eggsCartons.getEggs()[j][i]); // print a egg.
        }
        System.out.println("\n");
      }
    }
  }
}


