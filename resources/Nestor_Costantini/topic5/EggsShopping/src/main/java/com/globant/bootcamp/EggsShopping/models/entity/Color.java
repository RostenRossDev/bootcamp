package com.globant.bootcamp.EggsShopping.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="color_shop")
public class Color implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, length=20 , nullable = false)
	@NotBlank(message = "Color name must contain at least onenon-whitespace character")
	@NotEmpty(message = "Color name cannot be null")	
	private String color;
	
	@Column(nullable = false)
	private Boolean enable;
	
	@OneToMany(
	        mappedBy = "color",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<PriceEggs> prices;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
