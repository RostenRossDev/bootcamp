package com.globant.bootcamp.EggsShopping.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.bootcamp.EggsShopping.models.entity.Invoice;
import com.globant.bootcamp.EggsShopping.models.service.InvoiceService;

@RestController
@RequestMapping(value="/api/v1/invoice")
public class InvoiceController {
	private final Log LOG  = LogFactory.getLog(this.getClass());

	
	@Autowired
	InvoiceService invoiceSerivice;
	
	
	@GetMapping("/")
	public  ResponseEntity<?> allInvoices(){
		Map<String, Object> responseMap = new HashMap<>();

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOG.info("username: "+principal);
		
		

		try {
			String userName = principal.toString();
			List<Invoice> invoices = invoiceSerivice.findByUsername(userName);
			responseMap.put("invoices", invoices);
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);

		} catch (Exception e) {
			
			responseMap.put("error", e);
			responseMap.put("msj", "Upps !! Something was wrong !!");
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<?> invoiceById(@PathVariable("id") Long id ){
		Map<String, Object> responseMap = new HashMap<>();

		List<Invoice> invoices = invoiceSerivice.findByUser(id);
		responseMap.put("invoices", invoices);
		
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
}
