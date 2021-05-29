package com.globant.bootcamp.EggsShopping.models.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.globant.bootcamp.EggsShopping.models.dao.IInvoiceDao;

public class InvoiceService implements IInvoiceService{

	@Autowired
	IInvoiceDao invoiceDao;
}
