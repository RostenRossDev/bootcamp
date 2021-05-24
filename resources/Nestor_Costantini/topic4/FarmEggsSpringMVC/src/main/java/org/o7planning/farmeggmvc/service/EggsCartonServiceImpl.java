package org.o7planning.farmeggmvc.service;

import java.util.ArrayList;
import java.util.List;
import org.o7planning.farmeggmvc.database.DataFarm;
import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.model.EggsCarton;
import org.springframework.stereotype.Service;

@Service
public class EggsCartonServiceImpl implements IEggsCartonsService {

  @Override
  public void newEggsCartons(Color color) {
    // TODO Auto-generated method stub
    EggsCarton carton = new EggsCarton(color);
    DataFarm.eggsCartons.add(carton);
  }

  @Override
  public List<EggsCarton> allCartons() {
    // TODO Auto-generated method stub
    return DataFarm.eggsCartons;
  }

  @Override
  public List<EggsCarton> getCartonByColor(Color color) {
    // TODO Auto-generated method stub
    List<EggsCarton> cartons = new ArrayList<EggsCarton>();
    for (EggsCarton eggsCarton : DataFarm.eggsCartons) {
      if (eggsCarton.getColor().equals(color)) {
        cartons.add(eggsCarton);
      }
    }
    return cartons;
  }

  @Override
  public void deleteCarton(int index) {
    // TODO Auto-generated method stub
    DataFarm.eggsCartons.remove(index);
  }


}
