package com.globant.bootcamp.EggsShopping.models.animals;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.globant.bootcamp.EggsShopping.constants.StringConstans;
import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.EggsTray;

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

	private Color color;

	@ManyToOne
	@JoinColumn(name="carton_id")
	private EggsTray carton;

	public Long getId() {
		return id;
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
		Egg egg = (Egg) obj;
		if (obj instanceof Egg) {
			if (this.getColor() != null && egg.getColor() != null) {

				if (this.getColor().equals(egg.getColor())) {
					return true;
				}
			} else {
				return true;
			}
		}

		return false;
	}

}
