package org.o7planning.farmeggmvc.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.o7planning.farmeggmvc.database.DataFarm;
import org.o7planning.farmeggmvc.model.Farmer;
import org.o7planning.farmeggmvc.model.factory.EggFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmerServiceImpl implements IFarmerService {

  private final Logger LOG = Logger.getLogger(FarmerServiceImpl.class);

  @Autowired
  private EggFactory factory;

  @Override
  public void createFarmer(String name) {

    LOG.info(DataFarm.farmers.size());
    DataFarm.farmers.add(new Farmer(factory, name));
    LOG.info(DataFarm.farmers.size());
  }

  @Override
  public List<Farmer> allFarmer() {
    // TODO Auto-generated method stub
    return DataFarm.farmers;
  }

  @Override
  public void deleteFarmerByIndex(int index) {
    // TODO Auto-generated method stub
    DataFarm.farmers.remove(index);
  }

  @Override
  public Farmer updateFarmer(Farmer farmer, int index) {
    // TODO Auto-generated method stub
    Farmer oldFarmer = DataFarm.farmers.get(index);
    if (DataFarm.farmers.remove(oldFarmer)) {
      DataFarm.farmers.add(farmer);
      return farmer;
    }

    return null;
  }

  @Override
  public void deleteFarmerByName(String name) {
    // TODO Auto-generated method stub
    DataFarm.farmers.get(getFarmer(name));
  }

  private Integer getFarmer(String name) {
    Integer index = null;

    for (int i = 0; i < DataFarm.farmers.size(); i++) {

      if (DataFarm.farmers.get(i).getName().equals(name)) {
        return i;
      }
    }
    return null;
  }

@Override
public Farmer getFarmer(int index) {
	// TODO Auto-generated method stub
	return DataFarm.farmers.get(index);
}

}
