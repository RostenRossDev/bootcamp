package com.globant.bootcamp;

import com.globant.bootcamp.enums.Bird;
import com.globant.bootcamp.enums.Color;
import com.globant.bootcamp.model.EggsCarton;
import com.globant.bootcamp.model.FactoryPattern.EggFactory;
import com.globant.bootcamp.model.FactoryPattern.HenFactory;
import com.globant.bootcamp.model.Farmer;
import com.globant.bootcamp.model.animal.Hen;

public class MainTopic1 {

    public static void main(String[] args) {
        EggFactory eggFactory = new EggFactory();
        HenFactory henFactory = new HenFactory();

        Hen[] henHouse=initHenHouse(henFactory);
        layEggs(henHouse,eggFactory);
        Farmer farmer = new Farmer(eggFactory);
        farmer.startFarming(henHouse);
        showEggsCartons(farmer);

    }



    public static Hen[] initHenHouse(HenFactory henFactory){
        Hen[] hens=new Hen[40];
        for (int i = 0; i < 40; i++) {
            com.globant.bootcamp.model.animal.Bird hen = (com.globant.bootcamp.model.animal.Bird) henFactory.getAnimal(null, Bird.Hen);
            if(i<12){
                hens[i]=(Hen) henFactory.getAnimal(Color.WHITE, hen);
            }else{
                hens[i]=new Hen(Color.RED);
            }
        }
        return hens;
    }

    public static void layEggs(Hen[] hens, EggFactory eggFactory){
        for (Hen hen: hens) {
            hen.gaveBirth(hen.getEggsColor() ,eggFactory);
        }
    }

    public static void showEggsCartons(Farmer farmer){

        for (EggsCarton eggsCartons: farmer.getEggsCartons()) {
            System.out.println("================\n");
            for (int i = 0; i <6 ; i++) {
                for (int j = 0; j <5 ; j++) {
                    System.out.print(eggsCartons.getEggs()[j][i]);
                }
                System.out.println("\n");
            }
        }
    }
}
