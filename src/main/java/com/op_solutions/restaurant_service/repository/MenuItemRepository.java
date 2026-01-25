package com.op_solutions.restaurant_service.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.op_solutions.restaurant_service.models.MenuItem;
import com.op_solutions.restaurant_service.models.Table;

public interface MenuItemRepository  extends JpaRepository<MenuItem,Long >{

	Optional<Table> findByName(String name);

}
