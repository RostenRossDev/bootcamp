package com.globant.bootcamp.EggsShopping.models.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.bootcamp.EggsShopping.models.dao.IInvoiceDao;
import com.globant.bootcamp.EggsShopping.models.dao.IUserDao;
import com.globant.bootcamp.EggsShopping.models.entity.Invoice;
import com.globant.bootcamp.EggsShopping.models.entity.User;

@Service
public class InvoiceService implements IInvoiceService{
	private final Log LOG = LogFactory.getLog(this.getClass());

	
	@Autowired
	IInvoiceDao invoiceDao;
	
	@Autowired
	IUserDao userDao;
	
	@Override
	@Transactional
	public Invoice saveInvoice(Invoice invoice) {

		return invoiceDao.save(invoice);
	}
	
	@Override
	@Transactional (readOnly=true)
	public Invoice findInvoiceById(Long id) {
		// TODO Auto-generated method stub
		return invoiceDao.findById(id).orElse(null);
	}
	
	@Override
	public void deleteInvoice(Long id) {
		invoiceDao.deleteById(id);
	}

	@Override
	public List<Invoice> findByUser(Long id) {
		// TODO Auto-generated method stub
		User user = userDao.findById(id).orElse(null);
		
		return invoiceDao.findByUser(user);
	}
	
	public List<Invoice> allInvoice(){
		return (List<Invoice>) invoiceDao.findAll();
	}

	@Override
	public List<Invoice> findByUsername(String username) {
		// TODO Auto-generated method stub
		User user = userDao.findByUsername(username);
		LOG.info("user : "+user.getUsername());
		LOG.info("user : "+user.getNickname());
		LOG.info("user : "+user.getId());
		List<Invoice> invoices = invoiceDao.findByUser(user);
		LOG.info("invoices : "+invoices.size());

		return  invoices;
	}
	 
}
