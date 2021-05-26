package org.o7planning.farmeggmvc.service;

import java.util.List;
import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.model.animal.Hen;

public interface IHenService {

  public List<Hen> allHen();

  public List<Hen> henByColor(Color color);

  public void createHen();

  public void createHenByColor(Color color);

  public void deleteHenColor(Color color);

  public void layEggs();
  
  public Hen getHen(int index);
}
