package org.o7planning.farmeggmvc.controller;

import org.o7planning.farmeggmvc.database.DataFarm;
import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.model.EggsCarton;
import org.o7planning.farmeggmvc.model.factory.EggFactory;
import org.o7planning.farmeggmvc.service.EggsCartonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EggsCartonController {

  @Autowired
  EggFactory factory;

  @Autowired
  EggsCartonServiceImpl service;

  public String newEggCarton(Model model, @RequestParam("color") Color color) {

    service.newEggsCartons(color);

    return "";
  }

  @RequestMapping(value = "/createEggsCartons", method = RequestMethod.POST)
  public String createEggsCartons(Model model) {

    DataFarm.eggsCartons.add(new EggsCarton(Color.WHITE));
    DataFarm.eggsCartons.add(new EggsCarton(Color.RED));
    DataFarm.eggsCartons.add(new EggsCarton(Color.RED));

    return "redirect:/home";
  }

  public String allEggsCartons(Model model) {
    service.allCartons();

    return "";
  }

  public String eggsCartonByColor(Model model, @RequestParam("color") Color color) {

    service.getCartonByColor(color);

    return "";
  }


  public String deleteEggsCarton(Model model, Integer index) {
    service.deleteCarton(index);

    return "";
  }

}
