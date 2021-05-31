package com.globant.bootcamp.EggsShopping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.entity.EggsTray;
import com.globant.bootcamp.EggsShopping.models.entity.Invoice;
import com.globant.bootcamp.EggsShopping.models.entity.InvoiceItem;
import com.globant.bootcamp.EggsShopping.models.entity.tda.BuyTrayTDA;
import com.globant.bootcamp.EggsShopping.models.service.EggsTrayService;
import com.globant.bootcamp.EggsShopping.models.service.InvoiceService;

@RestController
@RequestMapping(value= "/api/v1/eggsShoping")
public class ShopingController {
	
	private final Log LOG  = LogFactory.getLog(this.getClass());
	
	@Autowired
	EggsTrayService eggTrayService;

	@Autowired
	InvoiceService invoiceService;
	
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
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@PostMapping("/BuyEggTray")
	public ResponseEntity<?> buyEggsTray(@RequestBody BuyTrayTDA buyTray){
		Map<String, Object> response = new HashMap<>();
		List<EggsTray> traysRed =new ArrayList<>();
		List<EggsTray> traysWhite = new ArrayList<>();
		Invoice newInvoice = new Invoice();
		InvoiceItem newInvoiceItem = new InvoiceItem();
		newInvoiceItem.setCartons(new ArrayList<EggsTray>());
		
		if(Color.STRING_RED.equals(buyTray.getColor1())  && buyTray.getQuantity1() != null && buyTray.getQuantity1()>0) {
			traysRed=eggTrayService.findByColorAndSold(Color.STRING_RED, Constants.FALSE,buyTray.getQuantity1());
			newInvoiceItem.setQuantity(buyTray.getQuantity1());
			newInvoiceItem.addCartons(traysRed);
		}
		
		if(Color.STRING_WHITE.equals(buyTray.getColor2())  && buyTray.getQuantity2() != null && buyTray.getQuantity2()>0) {
			traysWhite=eggTrayService.findByColorAndSold(Color.STRING_WHITE, Constants.FALSE,buyTray.getQuantity2());
			newInvoiceItem.setQuantity(buyTray.getQuantity2());
			newInvoiceItem.addCartons(traysWhite);
		}
		
		if(traysRed.size() >0 || traysWhite.size() > 0 ) {
			newInvoice.setItems(new ArrayList<InvoiceItem>());
			newInvoice.addIteminvoice(newInvoiceItem);

			if(traysRed.size()<1) {
				response.put("stockMsj1","Upps! sorry no stock of red trays");
				
			}

			if(traysWhite.size()<1) {
				response.put("stockMsj2", "Upps! sorry no stock of white trays");
			}

			newInvoice=invoiceService.saveInvoice(newInvoice);

			if(newInvoice != null) {

				response.put("msj","tray successfully purchased!");
				response.put("invoice", newInvoice);

				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
				
			}else {
				
				response.put("msj", "Oops !! An error has occurred in the purchase! try again please.");
				response.put("invoice", newInvoice);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		}
		
		response.put("msj", "Oops !! Sorry we don't have stock");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
