package com.globant.bootcamp.model.FactoryPattern;

import com.globant.bootcamp.enums.BirdEnum;
import com.globant.bootcamp.enums.Color;
import com.globant.bootcamp.model.animal.Animal;
import com.globant.bootcamp.model.animal.Bird;
import com.globant.bootcamp.model.animal.Hen;

public class HenFactory extends AnimalFactory{

    @Override
    public Animal getAnimal(Color color, Bird bird) {
        if( bird != null)
            return new Hen(color);

        return new Hen();
    }

    @Override
    public Animal getAnimal(Color color, Enum bird) {
        if( bird == BirdEnum.Hen)
            return new Hen(color);

        return new Hen();
    }
}
