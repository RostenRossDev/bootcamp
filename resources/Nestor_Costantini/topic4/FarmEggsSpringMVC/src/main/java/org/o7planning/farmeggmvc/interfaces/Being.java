package org.o7planning.farmeggmvc.interfaces;

import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.model.factory.AnimalFactory;

public interface Being<T> {
  T gaveBirth(Color color, AnimalFactory animalFactory);
}
