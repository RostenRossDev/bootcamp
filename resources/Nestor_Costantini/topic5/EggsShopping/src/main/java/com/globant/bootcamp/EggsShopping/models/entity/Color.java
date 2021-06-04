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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
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
	private List<EggsPrice> prices;
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		if (obj == this)
			return true;
		
		if (!(obj instanceof Color))
			return false;
		
		Color colorObj = (Color) obj;
		
		if ((id == null)?(colorObj.getId() != null): ! ((Long.compare(id, colorObj.getId())) == 0 ))
			return false;
		
		if ((color == null)?(colorObj.getColor() != null): !color.equals(colorObj.getColor()))
			return false;
		
		if ((enable == null)?(colorObj.getEnable() != null): !enable == colorObj.getEnable())
			return false;
		
		return true;

	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
