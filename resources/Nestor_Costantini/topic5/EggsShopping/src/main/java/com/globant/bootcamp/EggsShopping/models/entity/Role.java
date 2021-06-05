package com.globant.bootcamp.EggsShopping.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString@Builder
@Entity
@Table(name="role_shop")
public class Role implements Serializable{
	
	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, length=20 , nullable = false)
	@NotBlank(message = "Role name must contain at least onenon-whitespace character")
	@NotEmpty(message = "Role name cannot be null")	
	private String name;
	
	private static final long serialVersionUID = 1L;
}
