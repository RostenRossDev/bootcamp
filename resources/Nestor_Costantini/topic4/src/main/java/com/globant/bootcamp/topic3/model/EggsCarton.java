package com.globant.bootcamp.topic3.model;

import com.globant.bootcamp.topic3.constants.NumberConstants;
import com.globant.bootcamp.topic3.model.FactoryPattern.EggFactory;

public class EggsCarton {

  private final Logger LOG = Logger.getLogger(this.getClass());

  private Egg[][] eggs = new Egg[5][6];

  private int eggCount;

  private Boolean full;

  private Color color;

  private int[] freePlace = new int[2];

  public EggsCarton(Color color, EggFactory eggFactory) {

    this.eggCount = 0;

    this.freePlace[0] = 0;

    this.freePlace[1] = 0;

    this.full = false;

    this.color = color;

    this.fillEmptyEggs(eggFactory);

  }

  public Color getColor() {
    return this.color;
  }

  public Egg[][] getEggs() {

    return this.eggs;
  }

  public Boolean isFull() {

    return this.full;
  }

  public void addEgg(Egg egg) {
    if (this.color.equals(egg.getColor())) {

      this.eggs[this.freePlace[0]][this.freePlace[1]] = egg;

      eggCount++;

      this.updateFreePlace();

      fullControl();
    }
  }

  private void updateFreePlace() {

    if (this.freePlace[0] < 5) {

      this.freePlace[0]++;

      if (this.freePlace[0] > 4) {

        this.freePlace[0] = 0;

        this.freePlace[1]++;
      }
    }
  }

  private void fullControl() {

    if (NumberConstants.MAX_EGG == this.eggCount) {
      this.full = true;
      LOG.warn("Egg Carton is full!!!");
    }
  }

  private void fillEmptyEggs(EggFactory eggFactory) {
    for (int i = 0; i < 6; i++) {

      for (int j = 0; j < 5; j++) {
        this.eggs[j][i] = (Egg) eggFactory.getAnimal(null, Bird.Hen);
      }
    }
  }

  @Override
  public boolean equals(Object obj) {
    // TODO Auto-generated method stub
    boolean isEquals = false;

    EggsCarton eggCarton = (EggsCarton) obj;
    Egg[][] eggsObj = eggCarton.getEggs();
    if (obj instanceof EggsCarton) {
      if (this.color.equals(eggCarton.getColor())) {
        if (this.full.equals(eggCarton.isFull())) {

          for (int i = 0; i < this.eggs.length; i++) {
            for (int j = 0; j < this.eggs[i].length; j++) {
              if (this.eggs[i][j].equals(eggsObj[i][j])) {
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

}
