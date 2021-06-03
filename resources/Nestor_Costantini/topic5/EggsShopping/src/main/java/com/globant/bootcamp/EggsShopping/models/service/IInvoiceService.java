package com.globant.bootcamp.EggsShopping.models.service;

import java.util.List;

import com.globant.bootcamp.EggsShopping.models.entity.Invoice;

public interface IInvoiceService {

	public Invoice saveInvoice(Invoice invoice);
	
	public Invoice findInvoiceById(Long id);
	
	public Boolean deleteInvoice(Long id);
	
	public List<Invoice> findByUser(Long id);
	
	public List<Invoice> findByUsername(String username);

	public List<Invoice> allInvoice();

}
