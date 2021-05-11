package com.globant.bootcamp;

import com.globant.bootcamp.item.Color;
import com.globant.bootcamp.model.EggsCarton;
import com.globant.bootcamp.model.Farmer;
import com.globant.bootcamp.model.Hen;

public class Main {

  public static void main(String[] args) {


    Hen[] henHouse = initHenHouse(); // Create a hen house (array of Hen).

    layEggs(henHouse); // Hens lay eggs.

    Farmer farmer = new Farmer(); // Create a farmer.

    farmer.startFarming(henHouse); // farmer farming all eggs of hen house.

    showEggsCartons(farmer); // Show eggs carton content.
  }



  public static Hen[] initHenHouse() {
    Hen[] hens = new Hen[40]; // init the hen array.

    for (int i = 0; i < 40; i++) {
      if (i < 12) { // decide what color of eggs lays the hen.
        hens[i] = new Hen(Color.WHITE);
      } else {
        hens[i] = new Hen(Color.RED);
      }
    }
    return hens;
  }

  public static void layEggs(Hen[] hens) {

    for (Hen hen : hens) {
      hen.layEggs(); // each hen lay eggs.
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
