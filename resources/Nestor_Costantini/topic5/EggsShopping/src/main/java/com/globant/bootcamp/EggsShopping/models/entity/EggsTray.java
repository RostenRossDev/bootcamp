package com.globant.bootcamp.EggsShopping.models.entity;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globant.bootcamp.EggsShopping.enums.Color;

@Entity
@Table(name = "eggs_cartons")
public class EggsTray implements Serializable {

	private Log LOG = LogFactory.getLog(this.getClass());

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

	private Color color;

	private Boolean sold;

	//methods
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
		if (this.color.equals(egg.getColor()) && this.eggs.size() < 30) {

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
		if (obj == null)
			return false;
		LOG.info("not null");

		if (obj == this)
			return true;
		
		LOG.info("distinto");
		if (!(obj instanceof EggsTray))
			return false;
		LOG.info("instanceof");

		EggsTray trayObj = (EggsTray) obj;
		
		LOG.info("id carton");

		if ((id == null)?(trayObj.getId() != null): !id.equals(trayObj.getId()))
			return false;
		LOG.info("color carton");

		if ((color == null)?(trayObj.getColor() != null): !color.equals(trayObj.getColor()))
			return false;
		
		if ((price == null)?(trayObj.getPrice() != null): ((Double.compare(price, trayObj.getPrice())) != 0 ))
			return false;
		LOG.info("sold carton");
		
		LOG.info("sold: "+!((Boolean.compare(sold, trayObj.getSold())) !=0));
		LOG.info(((Boolean.compare(sold, trayObj.getSold()))));
		if ((sold == null)?(trayObj.getSold() != null): ((Boolean.compare(sold, trayObj.getSold())) !=0))
			return false;
		
		if ((eggs == null)?(trayObj.getEggs() != null): !eggs.equals(trayObj.getEggs()))
			return false;
		
		return true;
	}

}
