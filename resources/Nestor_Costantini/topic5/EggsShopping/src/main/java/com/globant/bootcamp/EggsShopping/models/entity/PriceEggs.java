package com.globant.bootcamp.EggsShopping.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.globant.bootcamp.EggsShopping.enums.Color;

@Entity
@Table(name="tray_price")
public class PriceEggs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Double price;
	
	private String description;
	
	private Color color;
	
	private Boolean actual;

	//methods
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Boolean getActual() {
		return actual;
	}

	public void setActual(Boolean actual) {
		this.actual = actual;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
