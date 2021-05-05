package com.globant.bootcamp.model.animal;

import com.globant.bootcamp.enums.Color;
import com.globant.bootcamp.enums.Gender;

public abstract class Bird extends Animal<Egg>{
    public Bird(Gender gender){
        super(gender);
    }

    @Override
    public Egg gaveBirth(Color color){
        if(this.gender ==Gender.FEMALE)
            return new Egg(new Hen(color), color);
        else
            return null;
    }
}
