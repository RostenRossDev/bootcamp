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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.constants.StringConstans;
import com.globant.bootcamp.EggsShopping.models.entity.Color;
//import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.entity.EggsTray;
import com.globant.bootcamp.EggsShopping.models.entity.Invoice;
import com.globant.bootcamp.EggsShopping.models.entity.InvoiceItem;
import com.globant.bootcamp.EggsShopping.models.entity.User;
import com.globant.bootcamp.EggsShopping.models.entity.tda.BuyTrayTDA;
import com.globant.bootcamp.EggsShopping.models.service.ColorService;
import com.globant.bootcamp.EggsShopping.models.service.EggsTrayService;
import com.globant.bootcamp.EggsShopping.models.service.InvoiceService;
import com.globant.bootcamp.EggsShopping.models.service.UserService;

@RestController
@RequestMapping(value= "/api/v1/eggsShoping")
public class ShopingController {
	
	private final Log LOG  = LogFactory.getLog(this.getClass());
	
	@Autowired
	EggsTrayService eggTrayService;

	@Autowired
	InvoiceService invoiceService;
	
	@Autowired 
	ColorService colorService;
	
	@Autowired
	UserService userService;
	
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
	public ResponseEntity<?> buyEggsTray(@Valid @RequestBody BuyTrayTDA buyTray){
		Map<String, Object> response = new HashMap<>();
		List<EggsTray> traysRed =new ArrayList<>();
		List<EggsTray> traysWhite = new ArrayList<>();
		Invoice newInvoice = Invoice.builder().items(new ArrayList<InvoiceItem>()).items(new ArrayList<InvoiceItem>()).build();
		InvoiceItem newInvoiceItem2 = InvoiceItem.builder().cartons(new ArrayList<EggsTray>()).build();
		String description1 = ""; 
		String description2 = ""; 
		
		if(buyTray.getQuantity1() != null && buyTray.getQuantity1()>0) {
			
			Color color = colorService.findByColor(StringConstans.RED);
			traysRed=eggTrayService.findByColorAndSold(color, Constants.FALSE,buyTray.getQuantity1());
			InvoiceItem newInvoiceItem = InvoiceItem.builder().cartons(new ArrayList<EggsTray>())
					.quantity(buyTray.getQuantity1())
					.build();

			newInvoiceItem.addCartons(traysRed);
			newInvoiceItem.setItemMout(newInvoiceItem.calculateAmount());
			description1="Red eggs tray :"+traysRed.size();
			newInvoice.addIteminvoice(newInvoiceItem);
			
			if(traysRed.size()<1) response.put("stockMsj1","Upps! sorry no stock of red trays");

		}
		
		if(buyTray.getQuantity2() != null && buyTray.getQuantity2()>0) {

			Color color = colorService.findByColor(StringConstans.RED);
			traysRed=eggTrayService.findByColorAndSold(color, Constants.FALSE,buyTray.getQuantity1());
			InvoiceItem newInvoiceItem = InvoiceItem.builder().cartons(new ArrayList<EggsTray>())
					.quantity(buyTray.getQuantity1())
					.build();

			newInvoiceItem.addCartons(traysRed);
			newInvoiceItem.setItemMout(newInvoiceItem.calculateAmount());
			description1="Red eggs tray :"+traysRed.size();
			newInvoice.addIteminvoice(newInvoiceItem);
			
			if(traysWhite.size()<1) response.put("stockMsj2", "Upps! sorry no stock of white trays");
			
		}
		 
		if(traysRed.size() >0 || traysWhite.size() > 0 ) {
			
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			LOG.info("username: "+principal);
			String userName = principal.toString();
			User user = userService.findByUsername(userName);
			newInvoice.setUser(user);
			newInvoice.setDescription(description1.concat("\n".concat(description2)));
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
