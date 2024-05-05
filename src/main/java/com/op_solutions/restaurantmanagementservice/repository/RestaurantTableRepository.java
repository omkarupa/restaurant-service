package com.op_solutions.restaurantmanagementservice.repository;

import com.op_solutions.restaurantmanagementservice.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable,Integer> {
}
