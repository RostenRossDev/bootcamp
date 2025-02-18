package com.globant.bootcamp.EggsShopping.models.entity;

import java.io.Serializable;
import java.util.List;

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

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString@Builder
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

	
}
