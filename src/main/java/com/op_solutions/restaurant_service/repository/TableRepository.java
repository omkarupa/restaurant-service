package com.op_solutions.restaurant_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.op_solutions.restaurant_service.models.Table;

public interface TableRepository extends JpaRepository<Table, Long> {

	Optional<Table> findByTableNumber(int i);

}
