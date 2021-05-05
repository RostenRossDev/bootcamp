package com.globant.bootcamp.model;

import com.globant.bootcamp.item.Color;

public class Farmer {
    private EggsCarton[] eggsCartons;

    public Farmer (){
        this.eggsCartons=new EggsCarton[3];
        this.initEggsCartons();
    }


    public void startFarming(Hen[] hens){
        int cont=0;
        for (Hen hen: hens) {
            Egg[] eggs= hen.handEgg();
            for (Egg egg:eggs) {
                cont++;
                if (hen.getEggsColor() == Color.RED) {
                    if (!this.eggsCartons[0].isFull()) {
                        this.eggsCartons[0].addEgg(egg);

                    } else if (!this.eggsCartons[1].isFull()){
                        this.eggsCartons[1].addEgg(egg);
                    }
                } else if (!this.eggsCartons[0].isFull()){
                    this.eggsCartons[2].addEgg(egg);
                }
            }
        }
    }

    public EggsCarton[] getEggsCartons( ){
        return this.eggsCartons;
    }
    private void initEggsCartons(){
        this.eggsCartons[0]=new EggsCarton(Color.RED);
        this.eggsCartons[1]=new EggsCarton(Color.RED);
        this.eggsCartons[2]=new EggsCarton(Color.WHITE);
    }
}
