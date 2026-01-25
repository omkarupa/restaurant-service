package com.op_solutions.restaurant_service.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class GuestSession {
	
	 @Id
	 private UUID sessionId;
	 private Long tableId;
	 private LocalDateTime startedAt;
	 private LocalDateTime endedAt;
	 private boolean active;

}
