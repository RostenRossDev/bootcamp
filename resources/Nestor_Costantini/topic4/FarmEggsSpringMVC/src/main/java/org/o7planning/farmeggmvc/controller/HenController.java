package org.o7planning.farmeggmvc.controller;

import org.apache.log4j.Logger;
import org.o7planning.farmeggmvc.database.DataFarm;
import org.o7planning.farmeggmvc.enums.Color;
import org.o7planning.farmeggmvc.service.FarmService;
import org.o7planning.farmeggmvc.service.HenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;;

@Controller
public class HenController {

  private static Logger LOG = Logger.getLogger(HenController.class);

  @Autowired
  private FarmService service;
  

  @RequestMapping(value = "/createHenHouse", method = RequestMethod.POST)
  public String createHenHouse(Model model) {

    for (int i = 0; i < 40; i++) {
      service.createHen();
    }
    model = fillModel(model);
    return "redirect:/home";
  }

  @RequestMapping(value = "/createHen", method = RequestMethod.POST)
  public String createHen(Model model) {

    service.createHen();

    model = fillModel(model);

    return "redirect:/home";
  }

  @RequestMapping(value = "/createHenByColor", method = RequestMethod.POST)
  public String createHenByColor(Model model, @RequestParam(value = "colorHen") Color color) {

    service.createHenByColor(color);

    return "redirect:/home";
  }

  @RequestMapping(value = "/deleteHen", method = RequestMethod.DELETE)
  public String deleteHen(Model model, @RequestParam(value = "colorHen") Color color) {

    service.deleteHenByColor(color);

    model = fillModel(model);

    return "redirect:/home";
  }

  @RequestMapping(value = "/layEggs", method = RequestMethod.POST)
  public String layHens(Model model) {

    service.layEggs();

    return "redirect:/home";
  }
  
  @RequestMapping(value="/hens", method = RequestMethod.GET)
  public String hensView(Model model){
	  model.addAttribute("hens", DataFarm.hens);
	  
	  return "hens";
  }
  
  @RequestMapping(value="hen/{id}",  method = RequestMethod.GET)
  public String viewHen(Model model, @PathVariable(value="id") int id) {
	  LOG.info("huevos : "+DataFarm.hens.get(id).getEgg().size());

	  model.addAttribute("hen", service.getHen(id));
	  model.addAttribute("egss", service.getHen(id).getEgg().size());
	  return "hen";
  }

  private Model fillModel(Model model) {
    model.addAttribute("redHens", DataFarm.redHen);
    model.addAttribute("whiteHens", DataFarm.redHen);
    model.addAttribute("eggsCartons", DataFarm.eggsCartons);
    model.addAttribute("farmers", DataFarm.farmers);
    model.addAttribute("eggs", DataFarm.eggs);
    model.addAttribute("hensSize", DataFarm.hens.size());
    model.addAttribute("greeting", "Welcome to Humpty Dumpty eggs farm's.");
    model.addAttribute("manage", "Here you can manage your Eggs Farms!!");
    model.addAttribute("pd", "pd: None egg was crashed :D !!");
    return model;
  }
}
