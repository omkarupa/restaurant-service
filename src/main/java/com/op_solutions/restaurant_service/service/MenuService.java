package com.op_solutions.restaurant_service.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.op_solutions.restaurant_service.dtos.MenuItemResponse;
import com.op_solutions.restaurant_service.exception.ResourceNotFoundException;
import com.op_solutions.restaurant_service.models.MenuItem;
import com.op_solutions.restaurant_service.repository.MenuItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {
	
	
	private final MenuItemRepository menuItemRepository;

	@Cacheable(value = "menuItems")
	public List<MenuItemResponse> getAllAvailableItems() {
		 System.out.println("Fetching from DB...");
		return menuItemRepository.findAll().stream().map(this::toResponse).toList();
	}

	@Cacheable(value = "menuItem", key = "#menuItemId")
	public MenuItemResponse getMenuItem(Long menuItemId) {
		System.out.println("Fetching from DB...");
		MenuItem item =  menuItemRepository.findById(menuItemId).orElseThrow(() -> new ResourceNotFoundException("Menu Item not found") );
		
		return toResponse(item);
	}
	
	 private MenuItemResponse toResponse(MenuItem item) {
	        MenuItemResponse res = new MenuItemResponse();
	        res.setId(item.getId());
	        res.setName(item.getName());
	        res.setPrice(item.getPrice());
	        res.setVeg(item.isVeg());
	        res.setAvailable(item.isAvailable());
	        res.setCategory(item.getCategory());
	        return res;
	    }

}
