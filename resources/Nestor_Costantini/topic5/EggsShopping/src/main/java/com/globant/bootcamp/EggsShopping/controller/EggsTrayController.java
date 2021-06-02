package com.globant.bootcamp.EggsShopping.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.models.entity.Color;
//import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.entity.Egg;
import com.globant.bootcamp.EggsShopping.models.entity.EggsTray;
import com.globant.bootcamp.EggsShopping.models.entity.PriceEggs;
import com.globant.bootcamp.EggsShopping.models.entity.tda.IntegerColorTDA;
import com.globant.bootcamp.EggsShopping.models.service.ColorService;
import com.globant.bootcamp.EggsShopping.models.service.EggsTrayService;
import com.globant.bootcamp.EggsShopping.models.service.InvoiceService;
import com.globant.bootcamp.EggsShopping.models.service.PriceEggService;
import com.globant.bootcamp.EggsShopping.models.service.UserService;


@RestController
@RequestMapping(value="/api/v1/eggTray")

public class EggsTrayController {
	
	private final Log LOG  = LogFactory.getLog(this.getClass());


	@Autowired
	EggsTrayService eggTrayService;
	
	@Autowired
	InvoiceService invoiceService;

	@Autowired
	UserService userService;
	
	@Autowired
	PriceEggService priceTrayServie;
	
	@Autowired
	ColorService colorService;
	 
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/")
	public  ResponseEntity<?> addEggsTrays (@Valid @RequestBody IntegerColorTDA integerColorTDA){
		
		Map<String, Object> response = new HashMap<>();
		List<EggsTray> trays = new ArrayList<EggsTray>();
		Color color =colorService.findByColor(integerColorTDA.getColor());
		for (int i = 0; i < integerColorTDA.getQuantity(); i++) {
			LOG.info("aca 1");
			
			Double price = priceTrayServie.priceByColor(color);
			LOG.info("price: "+price);
			EggsTray newTray = new EggsTray();
			newTray.setColor(color);
			newTray.setSold(false);
			newTray.setPrice(price);
			newTray = eggTrayService.saveEggTray(newTray);
			newTray.setEggs(new ArrayList<>()); 
			LOG.info("aca 2");

			for (int j = 0; j < integerColorTDA.getQuantity()*30; j++) {
				Egg newEgg = new Egg();
				newEgg.setColor(color);
				newEgg.setCarton(newTray);
				newTray.addEgg(newEgg);
			}
			LOG.info("aca 3");
			LOG.info("tray id: "+newTray.getId());
			Double priceTray = priceTrayServie.priceByColor(color);
			newTray.setPrice(priceTray);
			trays.add(newTray);
			LOG.info("aca 4");

		}
		trays=eggTrayService.saveTrayEggs(trays);
		if (trays != null ) {
			LOG.info("aca 5");

			response.put("trays", trays);
			response.put("msj", "EggsTrays was added successfully!");
			LOG.info("aca 6");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		};

		return new ResponseEntity<String>("Upps!! something was wrong.", HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/")
	public ResponseEntity<?>  allEggsTray (){
		List<EggsTray> trays = eggTrayService.findAll();
		
		return new ResponseEntity<List<EggsTray>>(trays, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/{color}")
	public ResponseEntity<?>  allEggsTrayByColor (@Valid @PathVariable("color") String sColor){
		
		Color color =colorService.findByColor(sColor.toUpperCase());
		LOG.info("color: "+color.getColor());
		LOG.info("color: "+color.getId());
		List<EggsTray> trays = eggTrayService.findByColor(color);
		
		return new ResponseEntity<List<EggsTray>>(trays, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/sold/{color}")
	public ResponseEntity<?>  allSoldEggsTrayByColor (@PathVariable("color") String sColor){
		
		Color color =colorService.findByColor(sColor.toUpperCase());

		List<EggsTray> trays = eggTrayService.findAllByColorAndSold(color, Constants.TRUE);

		return new ResponseEntity<List<EggsTray>>(trays, HttpStatus.OK);
	}

	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/stock")
	public ResponseEntity<?>  stockEggsTray (){
		List<EggsTray> trays = eggTrayService.findByStock();

		return new ResponseEntity<List<EggsTray>>(trays, HttpStatus.OK);
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/stock/{color}")
	public ResponseEntity<?>  stockEggsTrayByColor (@PathVariable("color") String sColor){
		Color color =colorService.findByColor(sColor.toUpperCase());

		List<EggsTray> trays = eggTrayService.findByStockByColor(color);

		return new ResponseEntity<List<EggsTray>>(trays, HttpStatus.OK);
	}
			
}
