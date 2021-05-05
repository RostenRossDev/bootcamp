package com.globant.bootcamp.model.FactoryPattern;

import com.globant.bootcamp.enums.Color;
import com.globant.bootcamp.model.animal.Animal;
import com.globant.bootcamp.model.animal.Bird;
import com.globant.bootcamp.model.animal.Egg;

public class EggFactory extends AnimalFactory{
    @Override
    public Animal getAnimal(Color color, Bird bird) {
        return new Egg(bird, color);
    }
}
