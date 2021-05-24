package org.o7planning.farmeggmvc.database;

import java.util.ArrayList;
import java.util.List;
import org.o7planning.farmeggmvc.model.EggsCarton;
import org.o7planning.farmeggmvc.model.Farmer;
import org.o7planning.farmeggmvc.model.animal.Egg;
import org.o7planning.farmeggmvc.model.animal.Hen;
import org.springframework.stereotype.Component;

@Component
public class DataFarm {

  public static List<Farmer> farmers = new ArrayList<Farmer>();

  public static List<EggsCarton> eggsCartons = new ArrayList();

  public static List<Hen> hens = new ArrayList();

  public static List<Egg> eggs = new ArrayList();

  public static int redHen = 0;

  public static int whiteHen = 0;

}
