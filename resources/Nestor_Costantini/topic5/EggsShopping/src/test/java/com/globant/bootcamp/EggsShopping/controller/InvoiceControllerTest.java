package com.globant.bootcamp.EggsShopping.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
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

	@InjectMocks
	private InvoiceController controller;

	private AutoCloseable closeable;

	private JacksonTester<Invoice> jsonInvoice;
	private JacksonTester<Collection<Invoice>> jsonInvoiceCollection;
	private JacksonTester<ErrorInfo> jsonApiError;
	private Invoice invoice;
	private Invoice anotherInvoice;

	private InvoiceItem item;

	private User user;

	@BeforeEach
	void setUp() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);
		JacksonTester.initFields(this, new ObjectMapper());

		mvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ErrorHandler()).build();

		item = new InvoiceItem();
		item.setQuantity(1);
		item.setId(1L);

		user = new User();
		user.setId(1L);
		user.setNickname("RostenRoss");
		user.setEnabled(true);
		user.setUsername("Nestor Matias");
		user.setPassword("12345");

		
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void invoiceByIdTest() throws Exception {
		invoice = new Invoice();
		invoice.setCreateAt(new Timestamp(new Date().getTime()));
		invoice.setDescription("Some text");
		invoice.setId(1L);
		invoice.setItems(new ArrayList<>());
		invoice.setUser(user);
		invoice.addIteminvoice(item);
		
		given(service.findByUser(1L)).willReturn(List.of(invoice));

		final ResultActions response = mvc.perform(get("/api/v1/invoice/user:1"));

		response.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(jsonInvoice.write(invoice).getJson()));

	}

}
