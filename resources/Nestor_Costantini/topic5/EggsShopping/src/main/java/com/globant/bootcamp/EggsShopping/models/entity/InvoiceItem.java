package com.globant.bootcamp.EggsShopping.models.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.globant.bootcamp.EggsShopping.models.EggsTray;

@Entity
@Table(name="invoice_items")
public class InvoiceItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="producto_id")
	private EggsTray carton;
	
	
	
	
	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Integer getQuantity() {
		return quantity;
	}




	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}




	public EggsTray getCarton() {
		return carton;
	}




	public void setCarton(EggsTray carton) {
		this.carton = carton;
	}




	public Double calculateAmount() {
		return quantity.doubleValue()*carton.getPrice();
	}
}
