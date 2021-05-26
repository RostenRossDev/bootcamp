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
    LOG.info("cantidad gallinas "+hens.size());
    List<Hen> henHouse = new ArrayList<>();
    for (Hen hen : hens) {
    	LOG.info("cantidad huevos antes de farmear : "+hen.getEgg().size());
    	List<Egg> eggs = hen.handEgg();
    	LOG.info("cantidad huevos despues de farmear : "+hen.getEgg().size());
        henHouse.add(hen);
        if (Color.RED.equals(hen.getEggsColor())) {
            if (!eggsCartons.get(1).isFull()) {
          	  
                LOG.info("added egg red");
                eggsCartons.get(1).addEgg(eggs.get(0));
                eggsCartons.get(1).addEgg(eggs.get(1));
                LOG.info("added egg red");

            } else if (	!eggsCartons.get(2).isFull()) {

                eggsCartons.get(2).addEgg(eggs.get(0));
                eggsCartons.get(2).addEgg(eggs.get(1));
                LOG.info("added egg red");
                LOG.info("added egg red");

            }
          } else  {

              eggsCartons.get(0).addEgg(eggs.get(0));
              eggsCartons.get(0).addEgg(eggs.get(1));
              LOG.info("added egg white");
              LOG.info("added egg white");

          }
	}
      
    LOG.info("End farming!!.");
    DataFarm.hens=henHouse;
    return eggsCartons;
  }
}
