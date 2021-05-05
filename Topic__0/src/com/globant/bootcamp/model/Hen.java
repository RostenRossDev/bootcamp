package com.globant.bootcamp.model;

import com.globant.bootcamp.item.Color;

import java.util.Arrays;

public class Hen {
    private Egg[] eggs;
    private Color color;

    public Hen(Color color){
        this.eggs= new Egg[2];
        this.color=color;
    }

    public Color getEggsColor(){
        return this.color;
    }

    public Egg[] getEgg(){
        return this.eggs;
    }




    public Egg[] handEgg(){
        Egg[] handEggs= this.eggs; //Guardo el arreglo de los huevos de la gallina en una variable local
        this.eggs=new Egg[2];
        return handEggs; //retorno la variable local con los huevos
    }






    public void layEggs(){
        this.eggs[0]=new Egg(this.color);
        this.eggs[1]=new Egg(this.color);
    }


}
