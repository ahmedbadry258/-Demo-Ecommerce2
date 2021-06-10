package com.example.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Full Name Must Not Be Empty ")
	@Size(min = 3,message = "Size Must be More Than 3 Letters")
	private String fullname;
	
	@Email(message = "Must Be Email Valid")
	@NotEmpty(message = "Email Can Not Be Empty")
	@Column(unique=true)
	private String email;
	
	@NotEmpty(message = "Password Can Not Be Empty")
	@Size(min = 3,message = "Size Must be More Than 3 Letters")
	private String password;
	
	@NotEmpty(message = "Phone Can Not Be Empty")
	private String phone;
	
	private String role;
	
	@Past(message = "Birth Date Must Be In The Past")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	
	private Date regiserDate;
	
	private boolean active;
	
	@OneToMany(cascade = CascadeType.ALL,targetEntity = Item.class,mappedBy = "user")
	//@JoinTable(name = "user_items",joinColumns = {@JoinColumn(name="user_id")},inverseJoinColumns = {@JoinColumn(name="items_id")})
	private List<Item> items;
	
	

}
