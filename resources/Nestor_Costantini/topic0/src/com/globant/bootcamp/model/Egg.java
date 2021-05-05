package com.globant.bootcamp.model;

import com.globant.bootcamp.item.Color;

public class Egg {
    private Color color;

    public Egg(Color color){
        this.color=color;
    }

    public Egg(){ }

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
