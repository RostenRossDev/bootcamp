package com.globant.bootcamp.EggsShopping.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Entity
@Table(name="tray_price")
public class EggsPrice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Positive(message = "Price Eggs must be greather than zero '0'.")
	@NotNull(message = "Price Eggs must not be null")
	private Double price;
	
	@Column(nullable = false)
	@NotBlank(message = "PriceEggs description must contain at least onenon-whitespace character")
	@NotEmpty(message = "PriceEggs description cannot be null")
	private String description;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "PriceEggs color must not be null")
	private Color color;
	
	@Column(nullable = false)
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

	@Override
	public String toString() {
		return "PriceEggs [id=" + id + ", price=" + price + ", description=" + description + ", color=" + color
				+ ", actual=" + actual + "]";
	}
	
	
	
}
