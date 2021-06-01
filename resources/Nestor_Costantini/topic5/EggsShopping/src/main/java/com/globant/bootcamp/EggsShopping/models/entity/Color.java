package com.globant.bootcamp.EggsShopping.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="color_shop")
public class Color implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, length=20 , nullable = false)
	@NotBlank(message = "Role name must contain at least onenon-whitespace character")
	@NotEmpty(message = "Role name cannot be null")	
	private String color;
	
	@Column(nullable = false)
	@NotNull(message = "Role enable cannot be null")
	private Boolean enable;

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
		color = color;
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
