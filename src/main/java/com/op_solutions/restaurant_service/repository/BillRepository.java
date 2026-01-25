package com.op_solutions.restaurant_service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.op_solutions.restaurant_service.models.Bill;

public interface BillRepository extends JpaRepository<Bill,Long > {

	Optional<Bill> findBySessionId(UUID sessionId);

}
	