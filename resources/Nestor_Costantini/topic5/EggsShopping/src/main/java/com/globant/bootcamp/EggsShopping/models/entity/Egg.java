package com.globant.bootcamp.EggsShopping.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.globant.bootcamp.EggsShopping.constants.StringConstans;
//import com.globant.bootcamp.EggsShopping.enums.Color;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString@Builder
@Entity
@Table(name="eggs_shop")
public class Egg implements Serializable{
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="color", nullable = false)
	private Color color;

	@ManyToOne
	@JoinColumn(name="carton_id", nullable = false)
	private EggsTray carton;

	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		
		if (obj == null)
			return false;
		
		if (obj == this)
			return true;
		
		if (!(obj instanceof Egg))
			return false;
		
		Egg trayObj = (Egg) obj;
		
		if ((id == null)?(trayObj.getId() != null): ! ((Long.compare(id, trayObj.getId())) == 0 ))
			return false;
		
		if ((color == null)?(trayObj.getColor() != null): !color.equals(trayObj.getColor()))
			return false;
		
		if ((carton == null)?(trayObj.getCarton() != null): !carton.equals(trayObj.getCarton()))
			return false;
		
		return true;
	}
}
