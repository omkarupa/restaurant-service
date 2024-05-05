package com.op_solutions.restaurantmanagementservice.repository;

import com.op_solutions.restaurantmanagementservice.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {
    List<MenuItem> findByCategory(String category);
}
