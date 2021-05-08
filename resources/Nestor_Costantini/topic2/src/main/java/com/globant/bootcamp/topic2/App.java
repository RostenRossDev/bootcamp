package com.globant.bootcamp.topic2;

import com.globant.bootcamp.topic2.enums.Color;
import model.EggsCarton;
import model.Farmer;
import model.FactoryPattern.EggFactory;
import model.FactoryPattern.HenFactory;
import model.animal.Bird;
import model.animal.Hen;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {

        EggFactory eggFactory = new EggFactory(); //Create an egg factory.

        HenFactory henFactory = new HenFactory(); //Create a hen Factory.

        Hen[] henHouse=initHenHouse(henFactory); // Create a hen house (array of Hen).

        layEggs(henHouse, eggFactory); //Hens lay eggs.

        Farmer farmer = new Farmer(eggFactory); //Create a farmer.

        farmer.startFarming(henHouse); //farmer farming all eggs of hen house.

        showEggsCartons(farmer); // Show eggs carton content.
    }



    public static Hen[] initHenHouse(HenFactory henFactory){
        Hen[] hens= new Hen[40]; //init the hen array.

        for (int i = 0; i < 40; i++) {
            Bird hen = (Bird) henFactory.getAnimal(null, com.globant.bootcamp.topic2.enums.Bird.Hen);
            if(i<12){ //decide what color of eggs lays the hen.
                hens[i] = (Hen) henFactory.getAnimal(Color.WHITE, hen);
            }else{
                hens[i] = (Hen) henFactory.getAnimal(Color.RED, hen);
            }
        }
        return hens;
    }

    public static void layEggs(Hen[] hens, EggFactory eggFactory){

        for (Hen hen: hens) {
            hen.layEggs(eggFactory); //each hen lay eggs.
        }
    }

    public static void showEggsCartons(Farmer farmer){

        for (EggsCarton eggsCartons: farmer.getEggsCartons()) {

            System.out.println("================\n");  //print a separator of eggs carton.
            for (int i = 0; i <6 ; i++) {
                for (int j = 0; j <5 ; j++) {
                    System.out.print(eggsCartons.getEggs()[j][i]); //print a egg.
                }
                System.out.println("\n");
            }
        }
    }
}


