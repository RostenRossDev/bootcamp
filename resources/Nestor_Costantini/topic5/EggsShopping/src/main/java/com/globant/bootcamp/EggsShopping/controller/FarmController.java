package com.globant.bootcamp.EggsShopping.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/v1/admin")
public class FarmController {

	@GetMapping("/startFarming")
	public  ResponseEntity<?> startFarmin (){
		
		
		return new ResponseEntity<String>("Start farming Successfuly", HttpStatus.CREATED);
	}
}
