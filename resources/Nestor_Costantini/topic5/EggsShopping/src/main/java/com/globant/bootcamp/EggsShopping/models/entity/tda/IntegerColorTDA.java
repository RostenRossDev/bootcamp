package com.globant.bootcamp.EggsShopping.models.entity.tda;


//import com.globant.bootcamp.EggsShopping.enums.Color;

public class IntegerColorTDA {

	private Integer quantity;
	
	private String color;
	
	private Boolean enable;
 
	
	
	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
}
