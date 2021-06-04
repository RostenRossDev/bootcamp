package com.globant.bootcamp.EggsShopping.models.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@Entity
@Table(name="invoices")
public class Invoice implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank(message = "Invoice description must contain at least onenon-whitespace character")
	@NotEmpty(message = "Invoice description cannot be null")
	private String description;

	@Column(name="create_at",nullable = false)
	@JsonFormat(pattern="yyy-MM-dd HH:mm:ss")
	private Timestamp createAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( nullable = false)
	private User user;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<InvoiceItem>items;
	
	@PrePersist
	public void prePersist(){
		createAt=new Timestamp(new Date().getTime());
	}
	
	public void addIteminvoice(InvoiceItem item){
		this.items.add(item);
	}
	
	public Double calculateTotal(){
		Double total=0.0;
		
		for (InvoiceItem item: items) {
			
			total+=item.calculateAmount();
		}
		
		return total;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == null)
			return false;
		
		if (obj == this)
			return true;
		
		if (!(obj instanceof Invoice))
			return false;
		
		Invoice trayObj = (Invoice) obj;
		
		if ((id == null)?(trayObj.getId() != null): ! ((Long.compare(id, trayObj.getId())) == 0 ))
			return false;
		
		if ((description == null)?(trayObj.getDescription() != null): !description.equals(trayObj.getDescription()))
			return false;
		
		if ((createAt == null)?(trayObj.getCreateAt() != null): !createAt.equals(trayObj.getCreateAt()))
			return false;
		
		if ((user == null)?(trayObj.getUser() != null): !user.equals(trayObj.getUser()))
			return false;
		
		if ((items == null)?(trayObj.getItems() != null): !items.equals(trayObj.getItems()))
			return false;
		
		
		return true;	
	}

	private static final long serialVersionUID=1L;
	
}
