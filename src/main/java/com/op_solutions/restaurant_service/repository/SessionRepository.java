package com.op_solutions.restaurant_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.op_solutions.restaurant_service.models.GuestSession;

public interface SessionRepository extends JpaRepository<GuestSession, UUID> {

}
