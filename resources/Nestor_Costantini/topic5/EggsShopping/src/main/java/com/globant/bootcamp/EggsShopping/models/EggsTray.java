package com.globant.bootcamp.EggsShopping.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.globant.bootcamp.EggsShopping.enums.Color;
import com.globant.bootcamp.EggsShopping.models.animals.Egg;
import com.globant.bootcamp.EggsShopping.models.entity.InvoiceItem;

@Entity
@Table(name="eggs_cartons")
public class EggsTray implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(
			mappedBy="carton",fetch=FetchType.LAZY,
			cascade=CascadeType.ALL
			)
	private List<Egg> eggs;
	
	@ManyToOne
	@JoinColumn(name="invoice_id")
	private InvoiceItem invoiceItem;
	
	private Double price;

	private Color color;

	private Boolean sold;
	
	
	public List<Egg> getEggs() {
		return eggs;
	}

	public void setEggs(List<Egg> eggs) {
		this.eggs = eggs;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void addEgg(Egg egg) {
		if (this.color.equals(egg.getColor()) && this.eggs.size()<30) {

			this.eggs.add(egg);
		}
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getSold() {
		return sold;
	}

	public void setSold(Boolean sold) {
		this.sold = sold;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		boolean isEquals = false;

		EggsTray eggCarton = (EggsTray) obj;
		List<Egg> eggsObj = eggCarton.getEggs();
		if (obj instanceof EggsTray) {
			if (this.color.equals(eggCarton.getColor())) {
				if (this.eggs.size()==eggCarton.getEggs().size()) {

					for (int i = 0; i < this.eggs.size(); i++) {
						if (this.eggs.get(i) instanceof Egg && eggsObj.get(i) instanceof Egg) {
							if (this.eggs.get(i).equals(eggsObj.get(i))) {
								isEquals = true;
							} else {
								isEquals = false;
								break;
							}
						}
					}
				}
			}
		}

		return isEquals;
	}

}
