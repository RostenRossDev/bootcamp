package org.o7planning.farmeggmvc.service;

import java.util.List;
import org.o7planning.farmeggmvc.model.Farmer;

public interface IFarmerService {

  public void createFarmer(String name);

  public List<Farmer> allFarmer();

  public void deleteFarmerByIndex(int index);

  public void deleteFarmerByName(String name);

  public Farmer updateFarmer(Farmer farmer, int index);
  
  public Farmer getFarmer(int index);

}
