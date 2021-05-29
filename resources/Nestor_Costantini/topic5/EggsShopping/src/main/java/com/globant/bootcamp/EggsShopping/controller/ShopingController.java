package com.globant.bootcamp.EggsShopping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.EggsTray;
import com.globant.bootcamp.EggsShopping.models.entity.tda.BuyTray;
import com.globant.bootcamp.EggsShopping.models.service.EggsTrayService;

@RestController
@RequestMapping("/api/v1/eggsShoping")
public class ShopingController {
	
	@Autowired
	EggsTrayService eggTrayService;

	@GetMapping("/home")
	public ResponseEntity<?>  home() {
		String hello = "Welcome to Humpty Dumpty Egg's Shop";
		String msj = "Please SingUp into shop eggs.";
		String url = "localhost:8080/api/v1/user/register";
		Map<String, String> response = new HashMap<>();
		response.put("weolcomeMsj", hello);
		response.put("message", msj);
		response.put("urlRegister", url);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/BuyEggTray")
	public ResponseEntity<?> buyEggsTray(@RequestBody BuyTray buyTray){
		Map<String, String> response = new HashMap<>();
		List<EggsTray> traysRed =new ArrayList();
		List<EggsTray> traysWhite = new ArrayList();
		if(Color.STRING_RED.equals(buyTray.getColor1())  && buyTray.getQuantity1() != null && buyTray.getQuantity1()>0) {
			traysRed=eggTrayService.findByColorAndSold(Color.STRING_RED, Constants.FALSE,buyTray.getQuantity1());
		}
		
		if(Color.STRING_WHITE.equals(buyTray.getColor2())  && buyTray.getQuantity2() != null && buyTray.getQuantity2()>0) {
			traysWhite=eggTrayService.findByColorAndSold(Color.STRING_WHITE, Constants.FALSE,buyTray.getQuantity2());
		}
		
		//generar factura
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
