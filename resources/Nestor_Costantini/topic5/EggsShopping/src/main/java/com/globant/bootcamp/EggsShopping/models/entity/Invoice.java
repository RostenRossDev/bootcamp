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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="invoices")
public class Invoice implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;

	@Column(name="create_at")
	@JsonFormat(pattern="yyy-MM-dd HH:mm:ss")
	private Timestamp createAt;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="factura_id")
	private List<InvoiceItem>items;
	
	@PrePersist
	public void prePersist(){
		createAt=new Timestamp(new Date().getTime());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void addItemFactura(InvoiceItem item){
		this.items.add(item);
	}
	
	public Double getTotal(){
		Double total=0.0;
		
		int size=items.size();
		for (int i = 0; i < size; i++) {
			total+=items.get(i).calculateAmount();
		}
		return total;
	}
	private static final long serialVersionUID=1L;

	
}
