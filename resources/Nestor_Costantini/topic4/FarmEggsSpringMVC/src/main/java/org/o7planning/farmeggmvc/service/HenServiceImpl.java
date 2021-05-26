package org.o7planning.farmeggmvc.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.o7planning.farmeggmvc.database.DataFarm;
import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.model.animal.Bird;
import org.o7planning.farmeggmvc.model.animal.Hen;
import org.o7planning.farmeggmvc.model.factory.AnimalFactory;
import org.o7planning.farmeggmvc.model.factory.EggFactory;
import org.o7planning.farmeggmvc.model.factory.HenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HenServiceImpl implements IHenService {

  private final Logger LOG = Logger.getLogger(HenServiceImpl.class);

  @Autowired
  EggFactory eggFactory;

  @Override
  public List<Hen> allHen() {
    // TODO Auto-generated method stub
    return DataFarm.hens;
  }

  @Override
  public List<Hen> henByColor(Color color) {
    // TODO Auto-generated method stub
    List<Hen> henByColor = new ArrayList<Hen>();

    for (Hen hen : DataFarm.hens) {
      if (hen.getEggsColor().equals((Color) color))
        henByColor.add(hen);
    }

    return henByColor;
  }

  @Override
  public void createHen() {
    AnimalFactory henFactory = new HenFactory();
    Bird hen = (Bird) henFactory.getAnimal(null, org.o7planning.farmeggmvc.enums.Bird.Hen);

    if (DataFarm.hens.size() < 40) {

      if (DataFarm.hens.size() < 12) {

        DataFarm.hens.add((Hen) henFactory.getAnimal(Color.WHITE, hen));
        LOG.info("Nro: "+DataFarm.hens.size()+" White hen was added.");
      } else {

        DataFarm.hens.add((Hen) henFactory.getAnimal(Color.RED, hen));
        LOG.info("Nro: "+DataFarm.hens.size()+" Red hen was added.");
      }
    }
  }

  @Override
  public void deleteHenColor(Color color) {

    if (color.equals(Color.RED)) {
      if (DataFarm.redHen > 0) {
        deleteHen(color);
      }
    } else {
      if (DataFarm.whiteHen > 0) {
        deleteHen(color);
      }
    }
  }

  private void deleteHen(Color color) {
    for (int i = 0; i < DataFarm.hens.size(); i++) {
      if (color.equals(DataFarm.hens.get(i).getEggsColor())) {
        DataFarm.hens.remove(i);
        break;
      }
    }
  }

  @Override
  public void createHenByColor(Color color) {

    AnimalFactory henFactory = new HenFactory();
    Bird hen = (Bird) henFactory.getAnimal(null, org.o7planning.farmeggmvc.enums.Bird.Hen);
    switch (color) {
      case RED:

        if (DataFarm.redHen < 28) {

          DataFarm.hens.add((Hen) henFactory.getAnimal(Color.RED, hen));
        }
        break;

      case WHITE:
        if (DataFarm.whiteHen < 12) {

          DataFarm.hens.add((Hen) henFactory.getAnimal(Color.RED, hen));
        }
        break;

    }

  }

  @Override
  public void layEggs() {
    // TODO Auto-generated method stub
    for (int i = 0; i < DataFarm.hens.size(); i++) {
      Hen hen = DataFarm.hens.get(i);
      hen.layEggs(eggFactory);
      DataFarm.hens.set(i, hen);
    }
  }

@Override
public Hen getHen(int index) {
	Hen hen = DataFarm.hens.get(index);
	return hen;
}

}
