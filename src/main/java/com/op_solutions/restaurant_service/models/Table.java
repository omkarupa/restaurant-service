package com.op_solutions.restaurant_service.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@jakarta.persistence.Table(name = "restaurant_table")
public class Table {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	 @Column(nullable = false, unique = true)
    private int tableNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TableStatus status;

}
