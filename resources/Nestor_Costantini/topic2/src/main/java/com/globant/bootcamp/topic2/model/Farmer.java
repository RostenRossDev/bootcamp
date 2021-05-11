package com.globant.bootcamp.topic2.model;


import org.apache.log4j.Logger;
import com.globant.bootcamp.topic2.enums.Color;
import com.globant.bootcamp.topic2.model.FactoryPattern.EggFactory;
import com.globant.bootcamp.topic2.model.animal.Egg;
import com.globant.bootcamp.topic2.model.animal.Hen;

public class Farmer {

  private final Logger LOG = Logger.getLogger(this.getClass());

  private EggsCarton[] eggsCartons;

  public Farmer(EggFactory eggFactory) {

    this.eggsCartons = new EggsCarton[3];

    this.initEggsCartons(eggFactory);

    LOG.info("Farmer is alive!!.");
  }


  public void startFarming(Hen[] hens) {
    LOG.info("Start farming!!.");

    for (Hen hen : hens) {
      Egg[] eggs = hen.handEgg();
      for (Egg egg : eggs) {
        if (Color.RED.equals(hen.getEggsColor())) {

          if (!this.eggsCartons[0].isFull()) {

            this.eggsCartons[0].addEgg(egg);

          } else if (!this.eggsCartons[1].isFull()) {

            this.eggsCartons[1].addEgg(egg);
          }
        } else if (!this.eggsCartons[0].isFull()) {

          this.eggsCartons[2].addEgg(egg);
        }
      }
    }

    LOG.info("End farming!!.");
  }

  public EggsCarton[] getEggsCartons() {

    return this.eggsCartons;
  }

  private void initEggsCartons(EggFactory eggFactory) {

    LOG.info("Start create Eggs Cartons.");

    this.eggsCartons[0] = new EggsCarton(Color.RED, eggFactory);

    this.eggsCartons[1] = new EggsCarton(Color.RED, eggFactory);

    this.eggsCartons[2] = new EggsCarton(Color.WHITE, eggFactory);

    LOG.info("Eggs Cartons was created!!.");

  }
}
