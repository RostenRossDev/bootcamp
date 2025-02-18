package com.globant.bootcamp.model;

import com.globant.bootcamp.item.Color;

public class EggsCarton {
    private Egg[][] eggs;
    private int eggCount;
    private boolean full;
    private Color color;
    private int[] freePlace = new int[2];

    public EggsCarton(Color color){
        this.eggCount =0;
        this.freePlace[0]=0;
        this.freePlace[1]=0;
        this.full=false;
        this.color=color;
        this.eggs=new Egg[5][6];
        this.fillEmptyEggs();
    }

    public Egg[][] getEggs(){
        return this.eggs;
    }

    public boolean isFull(){
        return this.full;
    }

    public void addEgg(Egg egg){
        if(egg.getColor() == this.color){
            this.eggs[this.freePlace[0]][this.freePlace[1]]=egg;
            eggCount++;
            this.updateFreePlace();
            fullControl();
        }
    }

    private void updateFreePlace(){
        if(this.freePlace[0]<5){
            this.freePlace[0]++;
            if (this.freePlace[0]>4){
                this.freePlace[0]=0;
                this.freePlace[1]++;
            }
        }
    }
    private void fullControl(){
        if (this.eggCount ==30){
            this.full=true;
        }
    }

    private void fillEmptyEggs(){
        for (int i = 0; i <6 ; i++) {
            for (int j = 0; j <5 ; j++) {
                this.eggs[j][i]=new Egg();
            }
        }
    }
}
