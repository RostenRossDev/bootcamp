package org.o7planning.farmeggmvc.model;


import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.o7planning.farmeggmvc.database.DataFarm;
import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.model.animal.Egg;
import org.o7planning.farmeggmvc.model.animal.Hen;
import org.o7planning.farmeggmvc.model.factory.EggFactory;


public class Farmer {

  private static List<String> names = new ArrayList<String>();

  private final Logger LOG = Logger.getLogger(this.getClass());

  private String name;

  public Farmer(EggFactory eggFactory, String name) {

    this.name = name;

    LOG.info("Farmer is alive!!.");
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<EggsCarton> startFarming(List<Hen> hens, List<EggsCarton> eggsCartons) {
    LOG.info("Start farming!!.");

    for (int i = 0; i < DataFarm.hens.size(); i++) {
      Hen hen = DataFarm.hens.get(i);
      Egg[] eggs = hen.handEgg();
      DataFarm.hens.set(i, hen);
      for (Egg egg : eggs) {
        if (Color.RED.equals(hen.getEggsColor())) {

          if (!eggsCartons.get(0).isFull()) {

            eggsCartons.get(0).addEgg(egg);

          } else if (eggsCartons.get(1).isFull()) {

            eggsCartons.get(1).addEgg(egg);
          }
        } else if (!eggsCartons.get(0).isFull()) {

          eggsCartons.get(2).addEgg(egg);
        }
      }
    }

    LOG.info("End farming!!.");

    return eggsCartons;
  }
}
