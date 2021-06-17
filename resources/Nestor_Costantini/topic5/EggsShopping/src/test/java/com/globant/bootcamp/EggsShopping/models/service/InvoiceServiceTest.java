package com.globant.bootcamp.EggsShopping.models.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.globant.bootcamp.EggsShopping.models.Repository.IInvoiceDao;
import com.globant.bootcamp.EggsShopping.models.Repository.IUserDao;
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
	
	private List<Invoice> emptyInvoice;

	private User user;

	private Optional<Invoice> invoiceOp;

	private Optional<User> userOp;

	// private Color color;

	// private EggsTray tray;

	@BeforeEach
	void setUp() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);
		
		emptyInvoice = List.of();

		invoice = Invoice.builder().id(1L).description("Some Text").createAt(new Timestamp(new Date().getTime()))
				.user(User.builder().build()).items(new ArrayList<>()).build();

		invoice.addIteminvoice(InvoiceItem.builder().build());

		invoiceOp = Optional.of(invoice);
		invoiceOp.orElse(null);
		
		user = User.builder().id(1L).nickname("RostenRoss").username("Nestor").build();
				
		userOp = Optional.of(user);
		userOp.orElse(null);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void saveInvoiceTestShouldReturnInvoiceWhenRepositoryPersist() {

		given(repository.save(invoice)).willReturn(invoice);

		Invoice invoiceTest = service.saveInvoice(invoice);

		assertEquals(invoice, invoiceTest);
	}

	@Test
	void saveInvoiceTestShouldReturnNullWhenRepositoryFailPersist() {

		given(repository.save(invoice)).willReturn(null);

		Invoice invoiceTest = service.saveInvoice(invoice);

		assertEquals(null, invoiceTest);
	}
	
	@Test
	void findInvoiceByIdTestShouldReturnInvoiceWhenRepositoryContainsMatches() {

		given(repository.findById(1L)).willReturn(invoiceOp);

		Invoice invoiceTest = service.findInvoiceById(1L);

		assertEquals(invoice, invoiceTest);
	}
	
	@Test
	void findInvoiceByIdTestShouldReturnWhenRepositoryNotContainsMatches() {
		
		invoiceOp =Optional.ofNullable(null);

		given(repository.findById(1L)).willReturn(invoiceOp);

		Invoice invoiceTest = service.findInvoiceById(1L);

		assertEquals(null, invoiceTest);
	}

	@Test
	void allInvoiceTestShouldReturnInvoiceListWhenRepositoryContainsMatches() {

		given(repository.findAll()).willReturn(List.of(invoice));

		List<Invoice> invoiceTest = service.allInvoice();

		assertEquals(List.of(invoice), invoiceTest);
	}
	
	@Test
	void allInvoiceTestShouldReturnEmptyInvoiceListWhenRepositoryNotContainsMatches() {

		given(repository.findAll()).willReturn(emptyInvoice);

		List<Invoice> invoiceTest = service.allInvoice();

		assertEquals(emptyInvoice, invoiceTest);
	}

	@Test
	void findByUsernameTestShouldReturnListWhenRepositoryContainsMatches() {
		
		given(repositoryUser.findByUsername("Nestor")).willReturn(user);

		given(repository.findByUser(user)).willReturn(List.of(invoice));

		List<Invoice> invoiceTest = service.findByUsername("Nestor");

		assertEquals(List.of(invoice), invoiceTest);
	}
	
	@Test
	void findByUsernameTestShouldReturnEmptyListWhenRepositoryNotContainsMatches() {
		
		given(repositoryUser.findByUsername("Nestor")).willReturn(user);

		given(repository.findByUser(user)).willReturn(emptyInvoice);

		List<Invoice> invoiceTest = service.findByUsername("Nestor");

		assertEquals(emptyInvoice, invoiceTest);
	}

	@Test
	void findByUserTestShouldReturnListWhenRepositoryContainsMatches() {

		given(repositoryUser.findById(1L)).willReturn(userOp);

		given(repository.findByUser(user)).willReturn(List.of(invoice));

		List<Invoice> invoiceTest = service.findByUser(1L);

		assertEquals(List.of(invoice), invoiceTest);
	}

	@Test
	void findByUserTestShouldReturnEmptyListWhenRepositoryNotContainsMatches() {

		given(repositoryUser.findById(1L)).willReturn(userOp);

		given(repository.findByUser(user)).willReturn(emptyInvoice);

		List<Invoice> invoiceTest = service.findByUser(1L);

		assertEquals(emptyInvoice, invoiceTest);
	}
	
	@Test
	void deleteRoleTestShouldThrowPersistenceExceptionWhenDeletePriceRepositoryFail() {
		  // perform the call
	 	service.deleteInvoice(1L);
        // verify the mocks
	 	Mockito.verify(repository, times(1))
        .deleteById(invoice.getId());
        //verify(repository, times(1)).delete(invoice);
	}
	
}
