package com.globant.bootcamp.model.FactoryPattern;

import com.globant.bootcamp.enums.Color;
import com.globant.bootcamp.model.animal.Animal;
import com.globant.bootcamp.model.animal.Bird;
import com.globant.bootcamp.model.animal.Egg;

public class EggFactory extends AnimalFactory{
    @Override
    public Animal getAnimal(Color color, Bird bird) {
        if(color !=null && bird != null )
            return new Egg(bird, color);

        return new Egg(null, null);
    }

    @Override
    public Animal getAnimal(Color color, Enum bird) {
        return null;
    }
}
