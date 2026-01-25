package com.op_solutions.restaurant_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.op_solutions.restaurant_service.dtos.MenuItemResponse;
import com.op_solutions.restaurant_service.service.MenuService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
public class MenuController {
	
	
	private final MenuService menuService;

	@GetMapping
	public List<MenuItemResponse> getMenu()
	{
		return menuService.getAllAvailableItems();
	}
	
	@GetMapping("/{id}")
	public MenuItemResponse getMenuitem(@PathVariable Long id)
	{
		return menuService.getMenuItem(id);
	}

}
