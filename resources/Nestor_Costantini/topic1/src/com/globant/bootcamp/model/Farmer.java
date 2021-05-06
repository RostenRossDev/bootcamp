package com.globant.bootcamp.model;


import com.globant.bootcamp.enums.Color;
import com.globant.bootcamp.model.FactoryPattern.EggFactory;
import com.globant.bootcamp.model.animal.Egg;
import com.globant.bootcamp.model.animal.Hen;

public class Farmer {

    private EggsCarton[] eggsCartons;

    public Farmer (EggFactory eggFactory){

        this.eggsCartons = new EggsCarton[3];

        this.initEggsCartons(eggFactory);

    }


    public void startFarming( Hen[] hens ){
        for ( Hen hen: hens ) {
            Egg[] eggs = hen.handEgg();
            for ( Egg egg:eggs ) {
                if ( Color.RED.equals(hen.getEggsColor()) ) {

                    if ( !this.eggsCartons[0].isFull() ) {

                        this.eggsCartons[0].addEgg( egg );

                    } else if ( !this.eggsCartons[1].isFull() ){

                        this.eggsCartons[1].addEgg( egg );
                    }
                } else if ( !this.eggsCartons[0].isFull() ){

                    this.eggsCartons[2].addEgg( egg );
                }
            }
        }
    }

    public EggsCarton[] getEggsCartons(){

        return this.eggsCartons;
    }

    private void initEggsCartons( EggFactory eggFactory ){

        this.eggsCartons[0] = new EggsCarton( Color.RED, eggFactory );

        this.eggsCartons[1] = new EggsCarton( Color.RED,eggFactory );

        this.eggsCartons[2] = new EggsCarton( Color.WHITE, eggFactory );

    }
}
