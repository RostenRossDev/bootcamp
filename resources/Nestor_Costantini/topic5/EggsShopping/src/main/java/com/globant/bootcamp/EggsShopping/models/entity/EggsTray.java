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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.globant.bootcamp.EggsShopping.enums.Color;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@Entity
@Table(name = "eggs_cartons")
public class EggsTray implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@OneToMany(mappedBy = "carton", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Egg> eggs;

	@ManyToOne
	@JoinColumn(name = "invoice_id")
	private InvoiceItem invoiceItem;

	private Double price;


	@ManyToOne
	@JoinColumn(name="color_id", nullable = false)
	@NotNull
	private Color color;

	private Boolean sold;

	public void addEgg(Egg egg) {
		
		if (this.color.equals(egg.getColor()) && this.eggs.size() < 30) this.eggs.add(egg);
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == null)
			return false;

		if (obj == this)
			return true;
		
		if (!(obj instanceof EggsTray))
			return false;

		EggsTray trayObj = (EggsTray) obj;
		

		if ((id == null)?(trayObj.getId() != null): !id.equals(trayObj.getId()))
			return false;

		if ((color == null)?(trayObj.getColor() != null): !color.equals(trayObj.getColor()))
			return false;
		
		if ((price == null)?(trayObj.getPrice() != null): ((Double.compare(price, trayObj.getPrice())) != 0 ))
			return false;
		
		if ((sold == null)?(trayObj.getSold() != null): ((Boolean.compare(sold, trayObj.getSold())) !=0))
			return false;
		
		if ((eggs == null)?(trayObj.getEggs() != null): !eggs.equals(trayObj.getEggs()))
			return false;
		
		return true;
	}

}
