package com.globant.bootcamp.EggsShopping.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.globant.bootcamp.EggsShopping.models.entity.Invoice;
import com.globant.bootcamp.EggsShopping.models.entity.User;

public interface IInvoiceDao extends CrudRepository<Invoice, Long> {
	
	public List<Invoice> findAll();
	
	public List<Invoice> findByUser(User user);

	
}
