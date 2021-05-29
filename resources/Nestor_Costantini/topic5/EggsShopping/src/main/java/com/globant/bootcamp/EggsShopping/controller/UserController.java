package com.globant.bootcamp.EggsShopping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.bootcamp.EggsShopping.models.entity.Invoice;
import com.globant.bootcamp.EggsShopping.models.entity.Role;
import com.globant.bootcamp.EggsShopping.models.entity.User;
import com.globant.bootcamp.EggsShopping.models.service.InvoiceService;
import com.globant.bootcamp.EggsShopping.models.service.RoleService;
import com.globant.bootcamp.EggsShopping.models.service.UserService;

@RestController
@RequestMapping(value="/api/v1/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleServie;
	
	@Autowired
	InvoiceService invoiceSerivice;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	
	@PostMapping("/register")
	public ResponseEntity<?> create(@Validated @RequestBody User user, BindingResult result) {
		Map<String, Object> response= new HashMap<>(); 
		User userNew = null;

		if(result.hasErrors()) {
			 
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
	        String passwordBcrypt = passwordEncoder.encode(user.getPassword());
	        user.setPassword(passwordBcrypt);
	        user.setEnabled(true);
	        List<Role> roles = new ArrayList<>();
	        roles.add(roleServie.findOne(1L));
	        user.setRoles(roles);
			userNew = userService.createUser(user);
		} catch(DataAccessException e) {
			response.put("mensaje", "Failed to insert into database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "User was created succsessfully!");
		response.put("usuario", userNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/allInvoices")
	public  ResponseEntity<?> allInvoices(@RequestBody Long idUser){
		
		List<Invoice> invoices = invoiceSerivice.findByUser(idUser);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("invoices", invoices);
		
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
}
