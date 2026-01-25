package com.op_solutions.restaurant_service.dtos;

import java.math.BigDecimal;

import lombok.Data;


@Data
public class MenuItemResponse {

	private Long id;
    private String name;
    private BigDecimal price;
    private boolean veg;
    private boolean available;
    private String category;
    
}
