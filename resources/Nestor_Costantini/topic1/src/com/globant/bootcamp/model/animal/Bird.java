package com.globant.bootcamp.model.animal;

import com.globant.bootcamp.enums.Color;
import com.globant.bootcamp.enums.Gender;
import com.globant.bootcamp.model.FactoryPattern.AnimalFactory;
import com.globant.bootcamp.model.FactoryPattern.EggFactory;

public abstract class Bird extends Animal<Egg>{
    public Bird(Gender gender){
        super(gender);
    }

    @Override
    public Egg gaveBirth(Color color, AnimalFactory animalFactory){
        EggFactory eggFactory= (EggFactory) animalFactory;
        if(this.gender ==Gender.FEMALE) {
            Hen mother = new Hen(color);
            return (Egg)eggFactory.getAnimal(color, mother);
        }
        return null;
    }
}
