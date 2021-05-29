package com.globant.bootcamp.EggsShopping.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.globant.bootcamp.EggsShopping.models.entity.Invoice;

public interface IInvoiceDao extends CrudRepository<Invoice, Long> {
	
	
}
