package org.o7planning.farmeggmvc.service;

import java.util.List;
import org.o7planning.farmeggmvc.database.DataFarm;
import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.model.EggsCarton;
import org.o7planning.farmeggmvc.model.Farmer;
import org.o7planning.farmeggmvc.model.animal.Hen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmService {

  @Autowired
  HenServiceImpl henService;

  @Autowired
  FarmerServiceImpl farmerService;

  public void newFarmer(String name) {
    farmerService.createFarmer(name);
  }

  public List<Farmer> allFarmer() {
    return farmerService.allFarmer();
  }

  public void deleteFarmer(int index) {
    farmerService.deleteFarmerByIndex(index);
  }

  public void deleteFarmerByName(String name) {
    farmerService.deleteFarmerByName(name);
  }

  public Farmer updateFarmer(Farmer farmer, int index) {
    return farmerService.updateFarmer(farmer, index);
  }

  public List<Hen> allHen() {
    return henService.allHen();
  }

  public List<Hen> henByColor(Color color) {
    return henService.henByColor(color);
  }

  public void createHen() {
    henService.createHen();
  }

  public void createHenByColor(Color color) {
    henService.createHenByColor(color);
  }

  public void deleteHenByColor(Color color) {
    henService.deleteHenColor(color);
  }

  public void startFarming(int index, List<EggsCarton> eggsCartons) {
    Farmer farmer = DataFarm.farmers.get(index);
    List<EggsCarton> cartons = farmer.startFarming(DataFarm.hens, eggsCartons);
    DataFarm.eggsCartons = cartons;
  }

  public Hen getHen(int index) {
	  return henService.getHen(index);
  }
  
  public void layEggs() {
    henService.layEggs();
  }
}
