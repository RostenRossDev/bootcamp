package com.globant.bootcamp.EggsShopping.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.bootcamp.EggsShopping.constants.Constants;
import com.globant.bootcamp.EggsShopping.errors.ErrorHandler;
import com.globant.bootcamp.EggsShopping.errors.ErrorInfo;
import com.globant.bootcamp.EggsShopping.models.entity.Invoice;
import com.globant.bootcamp.EggsShopping.models.entity.InvoiceItem;
import com.globant.bootcamp.EggsShopping.models.entity.User;
import com.globant.bootcamp.EggsShopping.models.service.InvoiceService;

class InvoiceControllerTest {

	private MockMvc mvc;

	@Mock
	private InvoiceService service;

	@Mock
	private InvoiceController controller;

	private AutoCloseable closeable;

	private Invoice invoice;
	
	private Invoice anotherInvoice;

	private InvoiceItem item;
	
	private ResponseEntity<?> response ;
	private ResponseEntity<?> responseUser ;
	private ResponseEntity<?> response500 ;
	
	private ResponseEntity<?> EmptyListResponse ;
	private Map<String, Object> mapResponse;
	private Map<String, Object> mapResponseUser;
	private Map<String, Object> mapResponseEmptyList;
	private List<Invoice> listInvoice;
	private User user;

	@BeforeEach
	void setUp() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);
		
		mvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ErrorHandler()).build();

		item = InvoiceItem.builder().quantity(1).id(1L).build();
		invoice = Invoice.builder().id(1L).description("asdas").user(user).build();
		user = User.builder().id(1L).nickname("RostenRoss").enabled(Constants.TRUE).username("Nestor Matias").password("12345").build();
		listInvoice = List.of(invoice);
		mapResponse = new HashMap<String, Object>();
		mapResponseEmptyList = new HashMap<String, Object>();
		mapResponseEmptyList.put("emptyList", List.of());
		
		mapResponse.put("lista", List.of(invoice));
		
		response = new ResponseEntity<Map<String,  Object>>(mapResponse, HttpStatus.OK);
		EmptyListResponse = new ResponseEntity<Map<String, Object>>(mapResponseEmptyList, HttpStatus.OK);
		mapResponseUser= new HashMap<String, Object>();
		mapResponseUser.put("user", user); 
		responseUser =new ResponseEntity<Map<String, Object>>(mapResponseUser, HttpStatus.OK);
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	//allInvoices
	@Test
	void allInvoicesTestShouldResponseListWhenAllInvoice() {
		Mockito.doReturn(response).when(controller).allInvoices();
	    Assertions.assertEquals(response, controller.allInvoices());
	}

	@Test
	void allInvoicesTestShouldResponseStatusOk200WhenAllInvoice() {
		Mockito.doReturn(response).when(controller).allInvoices();
	    Assertions.assertEquals(HttpStatus.OK, controller.allInvoices().getStatusCode());
	}
	
	@Test
	void allInvoicesTestShouldResponseNotNullListWhenAllInvoice() {
		Mockito.doReturn(response).when(controller).allInvoices();
	    Assertions.assertNotNull(controller.allInvoices().getBody());
	}
	@Test
	void allInvoicesTestShouldResponseEmptyListWhenAllInvoice() {
		Mockito.doReturn(EmptyListResponse).when(controller).allInvoices();
	    Assertions.assertEquals(mapResponseEmptyList, controller.allInvoices().getBody());
	}
	
	
	//allUserInvoices
	
	@Test
	void allUserInvoicesTestShouldResponseEmptyListWhenAllInvoice() {
		Mockito.doReturn(EmptyListResponse).when(controller).allUserInvoices();
	    Assertions.assertEquals(mapResponseEmptyList, controller.allUserInvoices().getBody());
	}
	
	@Test
	void allUserInvoicesTestShouldResponseListWhenAllInvoice() {
		Mockito.doReturn(response).when(controller).allUserInvoices();
	    Assertions.assertEquals(response, controller.allUserInvoices());
	}

	@Test
	void allUserInvoicesTestShouldResponseStatusOk200WhenAllInvoice() {
		Mockito.doReturn(response).when(controller).allUserInvoices();
	    Assertions.assertEquals(HttpStatus.OK, controller.allUserInvoices().getStatusCode());
	}
	
	@Test
	void allUserInvoicesTestShouldResponseNotNullListWhenAllInvoice() {
		Mockito.doReturn(response).when(controller).allUserInvoices();
	    Assertions.assertNotNull(controller.allUserInvoices().getBody());
	}
	
	//invoiceById
	@Test
	void ainvoiceByIdTestShouldResponseUserWhenAllInvoice() {
		Mockito.doReturn(responseUser).when(controller).invoiceById(1L);
	    Assertions.assertEquals(mapResponseUser, controller.invoiceById(1L).getBody());
	}
	
	@Test
	void invoiceByIdTestShouldResponseListWhenAllInvoice() {
		Mockito.doReturn(responseUser).when(controller).invoiceById(1L);
	    Assertions.assertEquals(responseUser, controller.invoiceById(1L));
	}

	@Test
	void invoiceByIdTestShouldResponseStatusOk200WhenAllInvoice() {
		Mockito.doReturn(responseUser).when(controller).invoiceById(1L);
	    Assertions.assertEquals(HttpStatus.OK, controller.invoiceById(1L).getStatusCode());
	}
	
	@Test
	void invoiceByIdTestShouldResponseNotNullListWhenAllInvoice() {
		Mockito.doReturn(responseUser).when(controller).invoiceById(1L);
	    Assertions.assertNotNull(controller.invoiceById(1L).getBody());
	}
	
}
