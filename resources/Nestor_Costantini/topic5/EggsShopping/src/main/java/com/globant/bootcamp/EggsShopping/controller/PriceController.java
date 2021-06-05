package com.globant.bootcamp.EggsShopping.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.bootcamp.EggsShopping.models.Repository.IColorDao;
import com.globant.bootcamp.EggsShopping.models.Repository.IEggPriceDao;
import com.globant.bootcamp.EggsShopping.models.entity.Color;
import com.globant.bootcamp.EggsShopping.models.entity.EggsPrice;
import com.globant.bootcamp.EggsShopping.models.entity.tda.IntegerColorTDA;
import com.globant.bootcamp.EggsShopping.models.entity.tda.PriceTda;

@RestController
@RequestMapping(value="/api/v1/price")
public class PriceController {

	@Autowired
	IEggPriceDao eggPriceDao;
	
	@Autowired
	IColorDao colorDao;
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/")
	public ResponseEntity<?> create(@Valid @RequestBody PriceTda priceTda) {
		Map<String, Object> response= new HashMap<>(); 
		
		try {
			Color color = colorDao.findByColor(priceTda.getColor());
			EggsPrice eggPrice = EggsPrice.builder().color(color).actual(priceTda.getActual())
					.description(priceTda.getDescription()).price(priceTda.getPrice()).build();
			
			eggPrice = eggPriceDao.save(eggPrice);
			
			if (eggPrice != null) {
				response.put("msj", "Price created successfuly!!");
				response.put("price", eggPrice);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			}
			response.put("msj", "Upps!! Sorry an error ocurr, tray again.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", "Failed to insert into database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/")
	public ResponseEntity<?> update(@Valid @RequestBody PriceTda priceTda) {
		Map<String, Object> response= new HashMap<>(); 

		try {
			Color color = colorDao.findByColor(priceTda.getColor());
			EggsPrice price =eggPriceDao.findByColorAndActual(color, priceTda.getActual());
			price.setColor(color);
			price.setActual(priceTda.getActual());
			
			price = eggPriceDao.save(price);
			
			if (price != null) {
				response.put("msj", "Price created successfuly!!");
				response.put("price", color);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			}
			response.put("msj", "Upps!! Sorry an error ocurr, tray again.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Failed to insert into database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/")
	public ResponseEntity<?> delete(@RequestBody PriceTda priceTda) {
		Map<String, Object> response= new HashMap<>(); 

		try {

			Color color = colorDao.findByColor(priceTda.getColor());
			colorDao.delete(color);
			EggsPrice price = eggPriceDao.findByColorAndActual(color, priceTda.getActual());
			eggPriceDao.delete(price);
			response.put("msj", "Price was deleted successfully!!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Failed to insert into database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
