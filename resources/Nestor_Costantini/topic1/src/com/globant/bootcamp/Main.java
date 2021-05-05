package com.globant.bootcamp;

import com.globant.bootcamp.item.Color;
import com.globant.bootcamp.model.EggsCarton;
import com.globant.bootcamp.model.Farmer;
import com.globant.bootcamp.model.Hen;

public class Main {

    public static void main(String[] args) {
        Hen[] henHouse=initHenHouse();
        layEggs(henHouse);
        Farmer farmer = new Farmer();
        farmer.startFarming(henHouse);
        showEggsCartons(farmer);

    }



    public static Hen[] initHenHouse(){
        Hen[] hens=new Hen[40];
        for (int i = 0; i < 40; i++) {
            if(i<12){
                hens[i]=new Hen(Color.WHITE);
            }else{
                hens[i]=new Hen(Color.RED);
            }
        }
        return hens;
    }

    public static void layEggs(Hen[] hens){
        for (Hen hen: hens) {
            hen.layEggs();
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
