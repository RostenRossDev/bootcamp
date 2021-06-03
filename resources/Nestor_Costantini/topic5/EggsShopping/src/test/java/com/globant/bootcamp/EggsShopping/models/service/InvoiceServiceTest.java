package com.globant.bootcamp.EggsShopping.models.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.globant.bootcamp.EggsShopping.models.Repository.IEggsTrayDao;
import com.globant.bootcamp.EggsShopping.models.Repository.IInvoiceDao;
import com.globant.bootcamp.EggsShopping.models.Repository.IUserDao;
import com.globant.bootcamp.EggsShopping.models.entity.Color;
import com.globant.bootcamp.EggsShopping.models.entity.Egg;
import com.globant.bootcamp.EggsShopping.models.entity.EggsTray;
import com.globant.bootcamp.EggsShopping.models.entity.Invoice;
import com.globant.bootcamp.EggsShopping.models.entity.InvoiceItem;
import com.globant.bootcamp.EggsShopping.models.entity.User;

class InvoiceServiceTest {

	@Mock
	private IInvoiceDao repository;

	@Mock
	private IUserDao repositoryUser;

	@InjectMocks
	private InvoiceService service;

	private AutoCloseable closeable;

	private Invoice invoice;

	private User user;

	private Optional<Invoice> invoiceOp;

	private Optional<User> userOp;

	// private Color color;

	// private EggsTray tray;

	@BeforeEach
	void setUp() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);

		invoice = new Invoice();

		invoice.setId(1L);
		invoice.setDescription("Some text");
		invoice.setCreateAt(new Timestamp(new Date().getTime()));
		invoice.setUser(new User());
		invoice.setItems(new ArrayList<>());
		invoice.addIteminvoice(new InvoiceItem());

		invoiceOp = Optional.of(invoice);
		invoiceOp.orElse(null);
		
		user = new User();
		user.setId(1L);
		user.setNickname("RostenRoss");
		user.setUsername("Nestor");
		
		userOp = Optional.of(user);
		userOp.orElse(null);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void saveInvoiceTest() {

		given(repository.save(invoice)).willReturn(invoice);

		Invoice invoiceTest = service.saveInvoice(invoice);

		assertEquals(invoice, invoiceTest);
	}

	@Test
	void findInvoiceByIdTest() {

		given(repository.findById(1L)).willReturn(invoiceOp);

		Invoice invoiceTest = service.findInvoiceById(1L);

		assertEquals(invoice, invoiceTest);
	}

	@Test
	void allInvoiceTest() {

		given(repository.findAll()).willReturn(List.of(invoice));

		List<Invoice> invoiceTest = service.allInvoice();

		assertEquals(List.of(invoice), invoiceTest);
	}

	@Test
	void findByUsernameTest() {
		
		given(repositoryUser.findByUsername("Nestor")).willReturn(user);

		given(repository.findByUser(user)).willReturn(List.of(invoice));

		List<Invoice> invoiceTest = service.findByUsername("Nestor");

		assertEquals(List.of(invoice), invoiceTest);
	}

	@Test
	void findByUserTest() {

		given(repositoryUser.findById(1L)).willReturn(userOp);

		given(repository.findByUser(user)).willReturn(List.of(invoice));

		List<Invoice> invoiceTest = service.findByUser(1L);

		assertEquals(List.of(invoice), invoiceTest);
	}

}
