package com.op_solutions.restaurant_service.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MenuItem {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private BigDecimal price;
	private boolean veg;
	private boolean available;
	private String category;
	
	public MenuItem(String name, BigDecimal price, boolean veg, boolean available, String category) {
		super();
		this.name = name;
		this.price = price;
		this.veg = veg;
		this.available = available;
		this.category = category;
	}
	

	
}
