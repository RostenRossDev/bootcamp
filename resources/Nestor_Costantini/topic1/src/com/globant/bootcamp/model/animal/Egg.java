package com.globant.bootcamp.model.animal;

import com.globant.bootcamp.enums.Color;

public class Egg extends Animal {
    private Color color;
    private Bird baby;

    public Egg(Bird bird, Color color){
        super(randomGender());
        this.baby=bird;
        this.color=color;
    }

    public Egg(){
        super(null);
    }

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

    @Override
    public Object gaveBirth(Color color) {
        return null;
    }

    @Override
    public void makeSound() {
        System.out.println("No sound.");
    }
}
