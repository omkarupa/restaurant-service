package com.op_solutions.restaurantmanagementservice.repository;

import com.op_solutions.restaurantmanagementservice.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff,Long> {
}
