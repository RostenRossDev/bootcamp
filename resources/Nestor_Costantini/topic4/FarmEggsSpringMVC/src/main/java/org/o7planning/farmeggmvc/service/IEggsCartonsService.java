package org.o7planning.farmeggmvc.service;

import java.util.List;
import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.model.EggsCarton;

public interface IEggsCartonsService {

  public void newEggsCartons(Color color);

  public List<EggsCarton> allCartons();

  public List<EggsCarton> getCartonByColor(Color color);

  public void deleteCarton(int index);

}
