package com.globant.bootcamp.EggsShopping.models.entity;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(name = "invoice_items")
public class InvoiceItem {
	private static final Log LOG = LogFactory.getLog(InvoiceItem.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Positive(message = "InvoiceItem quantity must be greather than zero '0'.")
	@NotNull(message = "InvoiceItem quantity must not be null")
	private Integer quantity;

	@OneToMany(mappedBy = "invoiceItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)

	private List<EggsTray> cartons;

	@Column(nullable = false)
	@Positive(message = "InvoiceItem quantity must be greather than zero '0'.")
	private Double itemMout;

	public Double getItemMout() {
		return itemMout;
	}

	public void setItemMout(Double itemMout) {
		this.itemMout = itemMout;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<EggsTray> getCartons() {
		return cartons;
	}

	public void setCartons(List<EggsTray> cartons) {
		this.cartons = cartons;
	}

	public void addCartons(List<EggsTray> cartons) {

		for (EggsTray eggsTray : cartons) {
			this.cartons.add(eggsTray);
		}
	}

	public void addCarton(EggsTray cartons) {

		this.cartons.add(cartons);

	}

	public Double calculateAmount() {

		Double price = cartons.get(0).getPrice();

		return quantity.doubleValue() * price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartons == null) ? 0 : cartons.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;

		if (obj == this)
			return true;

		if (!(obj instanceof InvoiceItem))
			return false;

		InvoiceItem invoiceObj = (InvoiceItem) obj;

		if ((id == null) ? (invoiceObj.getId() != null) : !((Long.compare(id, invoiceObj.getId())) == 0))
			return false;

		if ((quantity == null) ? (invoiceObj.getQuantity() != null)
				: ((Integer.compare(quantity, invoiceObj.getQuantity())) != 0))
			return false;

		if ((cartons == null) ? (invoiceObj.getCartons() != null) : !cartons.equals(invoiceObj.getCartons()))
			return false;

		return true;
	}

}
