package com.globant.bootcamp.model.animal;

import com.globant.bootcamp.enums.Color;

public class Egg {
    private Color color;
    private Hen baby;

    public Egg(Hen hen, Color color){
        this.baby=hen;
        this.color=color;
    }

    public Egg(){}

    public Color getColor(){
        return this.color;
    }
    @Override
    public String toString() {

        String eggStr;
        if(this.color==Color.RED){
            eggStr="(D)";
        }else if(this.color==Color.WHITE){
            eggStr="(O)";
        }else{
            eggStr="(G)";
        }
        return eggStr;
    }
}
