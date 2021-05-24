package org.o7planning.farmeggmvc.model;

import java.util.List;
import org.apache.log4j.Logger;
import org.o7planning.farmeggmvc.constants.NumberConstants;
import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.model.animal.Egg;


public class EggsCarton {

  private final Logger LOG = Logger.getLogger(this.getClass());

  private List<Egg> eggs;

  private Boolean full;

  private Color color;

  public EggsCarton(Color color) {

    this.full = false;

    this.color = color;
  }

  public Color getColor() {
    return this.color;
  }

  public List<Egg> getEggs() {

    return this.eggs;
  }

  public Boolean isFull() {

    return this.full;
  }

  public void addEgg(Egg egg) {

    if (this.color.equals(egg.getColor())) {

      this.eggs.add(egg);

      fullControl();
    }
  }

  private void fullControl() {

    if (NumberConstants.MAX_EGG == this.eggs.size()) {
      this.full = true;
      LOG.warn("Egg Carton is full!!!");
    }
  }

  @Override
  public boolean equals(Object obj) {
    // TODO Auto-generated method stub
    boolean isEquals = false;

    EggsCarton eggCarton = (EggsCarton) obj;
    List<Egg> eggsObj = eggCarton.getEggs();
    if (obj instanceof EggsCarton) {
      if (this.color.equals(eggCarton.getColor())) {
        if (this.full.equals(eggCarton.isFull())) {

          for (int i = 0; i < this.eggs.size(); i++) {
            if (this.eggs.get(i) instanceof Egg && eggsObj.get(i) instanceof Egg) {
              if (this.eggs.get(i).equals(eggsObj.get(i))) {
                isEquals = true;
              } else {
                isEquals = false;
                break;
              }
            }
          }
        }
      }
    }


    return isEquals;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return this.eggs.toString();
  }
}
