package org.o7planning.farmeggmvc.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.o7planning.farmeggmvc.database.DataFarm;
import org.o7planning.farmeggmvc.model.Farmer;
import org.o7planning.farmeggmvc.service.FarmerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FarmerController {

  private final Logger LOG = Logger.getLogger(FarmerController.class);

  @Autowired
  private FarmerServiceImpl service;

  @RequestMapping(value = "/newFarmer", method = RequestMethod.POST)
  public String newFarmer(Model model, @RequestParam("name") String name) {

    service.createFarmer(name);
    return "redirect:/home";
  }

  @RequestMapping(value="/farmers", method = RequestMethod.GET)
  public String allFarmer(Model model) {

    List<Farmer> farmers = DataFarm.farmers;
    model.addAttribute("farmers", farmers);
    model.addAttribute("cantidad", farmers.size());
    return "farmers";
  }

  public String deleteFarmer(Model model, @RequestParam int index) {

    service.deleteFarmerByIndex(index);
    return "";
  }

  public String deleteFarmerByName(Model model, @RequestParam String name) {

    service.deleteFarmerByName(name);
    return "";
  }

  public String updateFarmer(Model model, @RequestParam String name) {
    // nuevo farmer

    return "";
  }
  
  @RequestMapping(value="farmer/{id}",  method = RequestMethod.GET)
  public String viewHen(Model model, @PathVariable(value="id") int id) {
	  model.addAttribute("farmer", service.getFarmer(id));
	  return "farmer";
  }

}
