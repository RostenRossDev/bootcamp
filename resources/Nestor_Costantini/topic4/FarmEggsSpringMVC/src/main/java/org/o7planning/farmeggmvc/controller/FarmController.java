package org.o7planning.farmeggmvc.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.o7planning.farmeggmvc.database.DataFarm;
import org.o7planning.farmeggmvc.model.EggsCarton;
import org.o7planning.farmeggmvc.model.animal.Egg;
import org.o7planning.farmeggmvc.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FarmController {

  private Logger LOG = Logger.getLogger(FarmController.class.getName());

  @Autowired
  FarmService service;

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public String HelloFarm(Model model) {

    model = fillModel(model);
    LOG.info("hola");
    return "welcomeFarm";
  }

  @RequestMapping(value = "/startFarming", method = RequestMethod.POST)
  public String startFarming(Model model, @RequestParam("index") Integer index) {
    List<EggsCarton> cartons = DataFarm.eggsCartons;
    service.startFarming(index, cartons);
    return "redirect:/home";
  }

  @RequestMapping(value = "/showEggs", method = RequestMethod.GET)
  public String showEggs(Model model) {
    model = fillModel(model);
    
    for (EggsCarton carton :  DataFarm.eggsCartons) {
		for (Egg egg : carton.getEggs()) {
			LOG.info("O");
		}
	}

    return "showEggsCartons";
  }

  private Model fillModel(Model model) {
    model.addAttribute("redHens", DataFarm.redHen);
    model.addAttribute("whiteHens", DataFarm.whiteHen);
    model.addAttribute("eggsCartons", DataFarm.eggsCartons);
    model.addAttribute("farmers", DataFarm.farmers);
    model.addAttribute("hens", DataFarm.hens);
    model.addAttribute("hensSize", DataFarm.hens.size());
    model.addAttribute("greeting", "Welcome to Humpty Dumpty eggs farm's.");
    model.addAttribute("manage", "Here you can manage your Eggs Farms!!");
    model.addAttribute("pd", "pd: None egg was crashed :D !!");
    model.addAttribute("eggsCartonsSize", DataFarm.eggsCartons.size());
    model.addAttribute("eggsCartons", DataFarm.eggsCartons);
    return model;
  }
}
