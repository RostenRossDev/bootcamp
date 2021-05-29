package com.globant.bootcamp.EggsShopping.controller;


import java.util.ArrayList;
import java.util.List;

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
import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.EggsTray;
import com.globant.bootcamp.EggsShopping.models.animals.Egg;
import com.globant.bootcamp.EggsShopping.models.service.EggsTrayService;
import com.globant.bootcamp.EggsShopping.models.service.InvoiceService;
import com.globant.bootcamp.EggsShopping.models.service.UserService;

@RestController
@RequestMapping(value="/api/v1/eggTray")

public class EggsTrayController {

	@Autowired
	EggsTrayService eggTrayService;
	
	@Autowired
	InvoiceService invoiceService;

	@Autowired
	UserService userService;
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/")
	public  ResponseEntity<?> addEggsTrays (@RequestBody Integer quiantity, @RequestBody Color color){
		List<EggsTray> trays = new ArrayList<EggsTray>();
		for (int i = 0; i < quiantity; i++) {
			
			EggsTray newTray = new EggsTray();

			for (int j = 0; j < quiantity*30; j++) {
				Egg newEgg = new Egg();
				newEgg.setColor(color);
				newTray.addEgg(newEgg);
			}
			trays.add(newTray);
		}
		
		if (eggTrayService.saveTrayEggs(trays) != null ) {
			
			return new ResponseEntity<String>("EggsTrays was added successfully!", HttpStatus.CREATED);
		};

		return new ResponseEntity<String>("Upps!! something was wrong.", HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/")
	public ResponseEntity<?>  allEggsTray (){
		List<EggsTray> trays = eggTrayService.findAll();
		
		return new ResponseEntity<List<EggsTray>>(trays, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/{color}")
	public ResponseEntity<?>  allEggsTrayByColor (@PathVariable("color") Color color){
		List<EggsTray> trays = eggTrayService.findByColor(color);
		
		return new ResponseEntity<List<EggsTray>>(trays, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/sold/{color}")
	public ResponseEntity<?>  allSoldEggsTrayByColor (@PathVariable("color") Color color){
		List<EggsTray> trays = eggTrayService.findAllByColorAndSold(color, Constants.TRUE);

		return new ResponseEntity<List<EggsTray>>(trays, HttpStatus.OK);
	}

	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/stock")
	public ResponseEntity<?>  stockEggsTray (){
		List<EggsTray> trays = eggTrayService.findByStock();

		return new ResponseEntity<List<EggsTray>>(trays, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/stock/{color}")
	public ResponseEntity<?>  stockEggsTrayByColor (@PathVariable("color") Color color){
		List<EggsTray> trays = eggTrayService.findByStockByColor(color);

		return new ResponseEntity<List<EggsTray>>(trays, HttpStatus.OK);
	}
			
}
