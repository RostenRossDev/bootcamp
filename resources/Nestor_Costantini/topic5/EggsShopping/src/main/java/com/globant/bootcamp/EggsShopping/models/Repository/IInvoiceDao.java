package com.globant.bootcamp.EggsShopping.models.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.globant.bootcamp.EggsShopping.models.entity.Invoice;
import com.globant.bootcamp.EggsShopping.models.entity.User;

@Repository
public interface IInvoiceDao extends CrudRepository<Invoice, Long> {
	
	public List<Invoice> findByUser(User user);
	
	public List<Invoice> deleteByid(Long id);

	
}
