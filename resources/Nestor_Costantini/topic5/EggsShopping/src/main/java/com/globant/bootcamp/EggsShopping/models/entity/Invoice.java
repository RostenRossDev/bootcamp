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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="invoices")
public class Invoice implements Serializable{
	private Log LOG = LogFactory.getLog(this.getClass());

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
	private List<InvoiceItem>items;
	
	public List<InvoiceItem> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}

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
	
	public void addIteminvoice(InvoiceItem item){
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
	
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == null)
			return false;
		
		if (obj == this)
			return true;
		
		if (!(obj instanceof Invoice))
			return false;
		
		LOG.info("Casteo");
		Invoice trayObj = (Invoice) obj;
		
		LOG.info("id comparacion");
		if ((id == null)?(trayObj.getId() != null): ! ((Long.compare(id, trayObj.getId())) == 0 ))
			return false;
		
		LOG.info("description comparacion");
		LOG.info("description 1: "+description+", description 2: "+trayObj.getDescription());
		LOG.info(!description.equals(trayObj.getDescription()));
		if ((description == null)?(trayObj.getDescription() != null): !description.equals(trayObj.getDescription()))
			return false;
		
		LOG.info("createAt comparacion");
		LOG.info("createAt 1: "+createAt+", createAt 2: "+trayObj.getCreateAt());
		if ((createAt == null)?(trayObj.getCreateAt() != null): !createAt.equals(trayObj.getCreateAt()))
			return false;
		
		LOG.info("user comparacion");
		LOG.info("user 1: "+createAt+", user 2: "+trayObj.getUser());
		if ((user == null)?(trayObj.getUser() != null): !user.equals(trayObj.getUser()))
			return false;
		
		LOG.info("items comparacion");
		LOG.info("items 1: "+items+", items 2: "+trayObj.getItems());
		if ((items == null)?(trayObj.getItems() != null): !items.equals(trayObj.getItems()))
			return false;
		
		
		LOG.info("return true");
		return true;	
	}

	private static final long serialVersionUID=1L;
	
}
