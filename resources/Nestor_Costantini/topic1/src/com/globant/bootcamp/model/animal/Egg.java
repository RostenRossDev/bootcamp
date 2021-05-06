package com.globant.bootcamp.model.animal;

import com.globant.bootcamp.constants.StringConstans;
import com.globant.bootcamp.enums.Color;
import com.globant.bootcamp.model.FactoryPattern.AnimalFactory;

public class Egg extends Animal {

    private Color color;

    private Bird baby;

    public Egg( Bird bird, Color color ){

        super( randomGender() );

        this.baby=bird;

        this.color=color;
    }

    public Egg(){

        super(null );
    }

    public Color getColor(){

        return this.color;
    }

    @Override
    public String toString() {

        String eggStr=StringConstans.EMPTY_EGG;

        if( Color.RED.equals(this.color) ){

            eggStr=StringConstans.RED_EGG;
        }else if( Color.WHITE.equals(this.color) ){

            eggStr=StringConstans.WHITE_EGG;
        }

        return eggStr;
    }

    @Override
    public void makeSound() {

        System.out.println(StringConstans.EGG_SOUND);
    }

    @Override
    public Object gaveBirth(Color color, AnimalFactory animalFactory) {

        return null;
    }


}
