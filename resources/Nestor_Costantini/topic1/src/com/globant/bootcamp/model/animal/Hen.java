package com.globant.bootcamp.model.animal;

import com.globant.bootcamp.enums.Color;
import com.globant.bootcamp.enums.Gender;

public class Hen extends Bird{
    private Egg[] eggs;
    private Color color;

    public Hen(Color color){
        super(Gender.FEMALE);
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
        this.eggs[0]=this.gaveBirth(this.color);
        this.eggs[1]=this.gaveBirth(this.color);
    }


    @Override
    public void makeSound() {
        System.out.println("kakareo kakareo!!!");
    }
}
