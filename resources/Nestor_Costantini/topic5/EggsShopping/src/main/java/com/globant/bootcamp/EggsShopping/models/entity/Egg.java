package com.globant.bootcamp.EggsShopping.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.globant.bootcamp.EggsShopping.constants.StringConstans;
import com.globant.bootcamp.EggsShopping.enums.Color;

@Entity
@Table(name="eggs_shop")
public class Egg implements Serializable{
	/**
	 * 
	 */
	private Log LOG = LogFactory.getLog(this.getClass());

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Color color;

	@ManyToOne
	@JoinColumn(name="carton_id")
	private EggsTray carton;

	public Long getId() {
		return id;
	}

	public EggsTray getCarton() {
		return carton;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setCarton(EggsTray carton) {
		this.carton = carton;
	}

	@Override
	public String toString() {

		String eggStr = StringConstans.EMPTY_EGG;

		if (Color.STRING_RED.equals(this.color)) {

			eggStr = StringConstans.RED_EGG;
		} else if (Color.STRING_WHITE.equals(this.color)) {

			eggStr = StringConstans.WHITE_EGG;
		}

		return eggStr;
	}

	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		
		if (obj == null)
			return false;
		
		if (obj == this)
			return true;
		
		if (!(obj instanceof Egg))
			return false;
		
		LOG.info("Casteo");
		Egg trayObj = (Egg) obj;
		
		LOG.info("id comparacion");
		if ((id == null)?(trayObj.getId() != null): ! ((Long.compare(id, trayObj.getId())) == 0 ))
			return false;
		
		LOG.info("color comparacion");
		LOG.info("color 1: "+color+", color 2: "+trayObj.getColor());
		LOG.info(!color.equals(trayObj.getColor()));
		if ((color == null)?(trayObj.getColor() != null): !color.equals(trayObj.getColor()))
			return false;
		
		LOG.info("carton comparacion");
		LOG.info("carton 1: "+carton+", carton2: "+trayObj.getCarton());
		if ((carton == null)?(trayObj.getCarton() != null): !carton.equals(trayObj.getCarton()))
			return false;
		
		LOG.info("return true");
		return true;
	}
}
