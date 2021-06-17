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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString@Builder
@Entity
@Table(name="users_shop")
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique= true, length=20, nullable = false)
	@NotBlank(message = "User username must contain at least one non-whitespace character")
	@NotEmpty(message = "User username cannot be null")
	private String username;
	
	@Column(unique= true, length=20, nullable = false)
	@NotBlank(message = "User nickname must contain at least one non-whitespace character")
	@NotEmpty(message = "User nickname cannot be null")
	private String nickname;
	
	@Column(length=60, nullable = false)
	@JsonIgnore
	@NotBlank(message = "User password must contain at least one non-whitespace character")
	@NotEmpty(message = "User password cannot be null")	
	private String password;
	
	@Column(nullable = false)
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="users_roles", 
	joinColumns = @JoinColumn(name="user_id")
	, inverseJoinColumns = @JoinColumn(name="role_id")
	, uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","role_id"})})
	private List<Role> roles;

	public void updateUser(User user) {
		if(user.getNickname() != null) {
			this.nickname = user.getNickname();
		}
		
		if(user.getUsername() != null) {
			this.username = user.getUsername();
		}
		
		if(user.getEnabled() != null) {
			this.enabled = user.getEnabled();
		}
		
		if(user.getPassword() != null) {
			this.password = user.getPassword();
		}
	}

	private static final long serialVersionUID = 1L;

	public Object thenReturn(ResponseEntity<?> response) {
		// TODO Auto-generated method stub
		return null;
	}
}
