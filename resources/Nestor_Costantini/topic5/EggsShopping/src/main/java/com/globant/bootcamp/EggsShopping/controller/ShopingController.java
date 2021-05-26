package com.globant.bootcamp.EggsShopping.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shoping")
public class ShopingController {

	@GetMapping("/home")
	public ResponseEntity<?>  home() {
		String hello = "Welcome to Humpty Dumpty Egg's Shop";
		
		return new ResponseEntity<>(hello, HttpStatus.OK);
	}
}
