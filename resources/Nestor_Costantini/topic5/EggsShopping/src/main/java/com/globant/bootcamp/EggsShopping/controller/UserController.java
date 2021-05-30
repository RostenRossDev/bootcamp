package com.globant.bootcamp.EggsShopping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.bootcamp.EggsShopping.models.entity.Role;
import com.globant.bootcamp.EggsShopping.models.entity.User;
import com.globant.bootcamp.EggsShopping.models.service.InvoiceService;
import com.globant.bootcamp.EggsShopping.models.service.RoleService;
import com.globant.bootcamp.EggsShopping.models.service.UserService;

@RestController
@RequestMapping(value="/api/v1/user")
public class UserController {
	
	private final Log LOG  = LogFactory.getLog(this.getClass());
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleServie;
	
	@Autowired
	InvoiceService invoiceSerivice;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	
	@PostMapping("/")
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
	
	
	
	@Secured({"ADMIN","USER"})
	@PutMapping("/")
	public  ResponseEntity<?> update(@RequestBody User user){
		Map<String, Object> responseMap = new HashMap<>();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOG.info("username: "+principal);
		String userName = principal.toString();
		User updateUser = userService.findByUsername(userName);
		
		updateUser.updateUser(user);
		updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		try {
			userService.update(updateUser);
			responseMap.put("msj", "Information Updated successfully!.");
			responseMap.put("user", updateUser);
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);

		} catch (Exception e) {
			responseMap.put("msj", "Upps !! Something was wrong!!");
			responseMap.put("error", e);

			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		}

	}
	
	@Secured({"ADMIN","USER"})
	@DeleteMapping("/")
	public  ResponseEntity<?> delete(){
		Map<String, Object> responseMap = new HashMap<>();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOG.info("username: "+principal);
		String userName = principal.toString();
		User user = userService.findByUsername(userName);
		try {
			userService.delete(user);
			responseMap.put("msj", "User Was deleted successfully!.");
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			responseMap.put("msj", "Upps !! Something was wrong!!");
			responseMap.put("error", e);

			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		}
	}
	
	@Secured("ADMIN")
	@GetMapping("/all")
	public  ResponseEntity<?> getAll(){
		Map<String, Object> responseMap = new HashMap<>();
		List<User> users = userService.allUsers();
		responseMap.put("users", users);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
	
	@Secured("ADMIN")
	@GetMapping("/{id}")
	public  ResponseEntity<?> userById(@PathVariable("id") Long id){
		Map<String, Object> responseMap = new HashMap<>();
		User user = userService.findById(id);
		responseMap.put("user", user);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
	
	@Secured("ADMIN")
	@GetMapping("/{nickname}")
	public  ResponseEntity<?> userByNickname(@PathVariable("nickname") String nickname){
		Map<String, Object> responseMap = new HashMap<>();
		User user = userService.findByNickName(nickname);
		responseMap.put("user", user);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
}
