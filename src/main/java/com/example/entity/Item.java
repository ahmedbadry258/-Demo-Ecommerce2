package com.example.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Product> products;
	
	private int quantity;
	
	private double total;
	@DateTimeFormat(pattern = "yyyy/MMM/dd HH:mm:ss")
	private Date orderDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	
}
