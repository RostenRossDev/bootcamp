package com.globant.bootcamp.EggsShopping.models.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.globant.bootcamp.EggsShopping.models.EggsTray;

@Entity
@Table(name="invoice_items")
public class InvoiceItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantity;
	
	@OneToMany(
			mappedBy="invoiceItem",fetch=FetchType.LAZY,
			cascade=CascadeType.ALL
			)
	private List<EggsTray> cartons;
	
	
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




	public List<EggsTray> getCartons() {
		return cartons;
	}




	public void setCartons(List<EggsTray> cartons) {
		this.cartons = cartons;
	}




	public Double calculateAmount() {
		Double price = cartons.get(0).getPrice();
		return quantity.doubleValue()*price;
	}
}
