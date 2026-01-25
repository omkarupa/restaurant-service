package com.op_solutions.restaurant_service.config;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.op_solutions.restaurant_service.models.MenuItem;
import com.op_solutions.restaurant_service.models.Table;
import com.op_solutions.restaurant_service.models.TableStatus;
import com.op_solutions.restaurant_service.repository.MenuItemRepository;
import com.op_solutions.restaurant_service.repository.TableRepository;

@Configuration
public class RestaurantConfig {

	
	@Bean
	CommandLineRunner initTables(TableRepository tableRepository)
	{
		return args -> {
			for(int i = 1;i<=10;i++)
			{
				boolean exists = tableRepository.findByTableNumber(i).isPresent();
				
				if (!exists) {
                    Table table = new Table();
                    table.setTableNumber(i);
                    table.setStatus(TableStatus.AVAILABLE);

                    tableRepository.save(table);
                }
			}
			 System.out.println("✅ Restaurant tables initialized");
		};
	}
	
	 	@Bean
	    CommandLineRunner initMenuItems(MenuItemRepository menuItemRepository) {
		
	 		 return args -> {
	 		List<MenuItem> menuItems = List.of(
		            new MenuItem("Paneer Tikka", new BigDecimal("250.00"), true, true, "STARTER"),
		            new MenuItem( "Veg Spring Roll", new BigDecimal("180.00"), true, true, "STARTER"),
		            new MenuItem( "Chicken Lollipop", new BigDecimal("320.00"), false, true, "STARTER"),

		            new MenuItem("Butter Chicken", new BigDecimal("420.00"), false, true, "MAIN_COURSE"),
		            new MenuItem( "Paneer Butter Masala", new BigDecimal("380.00"), true, true, "MAIN_COURSE"),
		            new MenuItem( "Dal Tadka", new BigDecimal("220.00"), true, true, "MAIN_COURSE"),

		            new MenuItem( "Veg Biryani", new BigDecimal("300.00"), true, true, "RICE"),
		            new MenuItem( "Chicken Biryani", new BigDecimal("380.00"), false, true, "RICE"),

		            new MenuItem( "Butter Naan", new BigDecimal("60.00"), true, true, "BREAD"),
		            new MenuItem( "Plain Rice", new BigDecimal("150.00"), true, true, "RICE"),

		            new MenuItem( "Gulab Jamun", new BigDecimal("120.00"), true, true, "DESSERT"),
		            new MenuItem( "Ice Cream", new BigDecimal("100.00"), true, true, "DESSERT"),

		            new MenuItem( "Cold Drink", new BigDecimal("80.00"), true, true, "BEVERAGE"),
		            new MenuItem( "Mineral Water", new BigDecimal("40.00"), true, true, "BEVERAGE"),
		            new MenuItem( "Masala Papad", new BigDecimal("90.00"), true, true, "STARTER"));
	 		
	 		for (MenuItem item : menuItems) {
	            if (menuItemRepository.findByName(item.getName()).isEmpty()) {
	                menuItemRepository.save(item);
	            }
	        }

	        System.out.println("✅ Menu items initialized successfully");
		  
		    
	 	};
	 	}
	    
	 
}
