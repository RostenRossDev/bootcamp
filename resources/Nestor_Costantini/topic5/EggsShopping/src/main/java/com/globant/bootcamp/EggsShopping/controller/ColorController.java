package com.globant.bootcamp.EggsShopping.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.models.entity.Color;
import com.globant.bootcamp.EggsShopping.models.entity.tda.IntegerColorTDA;
import com.globant.bootcamp.EggsShopping.models.service.ColorService;

@RestController
@RequestMapping(value="/api/v1/color")
public class ColorController {

	@Autowired
	@Qualifier("colorService")
	private ColorService colorService; 
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/")
	public ResponseEntity<?> create(@Valid @RequestBody IntegerColorTDA integerColorTda) {
		Map<String, Object> response= new HashMap<>(); 

		Color color = Color.builder().color(integerColorTda.getColor().toUpperCase()).enable(Constants.TRUE).build();
			
		try {
			color = colorService.CreateColor(color);
			if (color != null) {
				response.put("msj", "Color created successfuly!!");
				response.put("color", color);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			}
			response.put("msj", "Upps!! Sorry an error ocurr, tray again.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Failed to insert into database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/")
	public ResponseEntity<?> update(@Valid @RequestBody IntegerColorTDA integerColorTda) {
		Map<String, Object> response= new HashMap<>(); 

		try {
			Color color =colorService.findByColor(integerColorTda.getColor());
			color.setColor(integerColorTda.getColor().toUpperCase());
			color.setEnable(integerColorTda.getEnable());
			
			color = colorService.CreateColor(color);
			
			if (color != null) {
				response.put("msj", "Color created successfuly!!");
				response.put("color", color);
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
	public ResponseEntity<?> delete(@RequestBody String sColor) {
		Map<String, Object> response= new HashMap<>(); 

		try {

			Color color = colorService.findByColor(sColor);
			colorService.deleteColor(color);
			response.put("msj", "Color was deleted successfully!!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Failed to insert into database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

}
