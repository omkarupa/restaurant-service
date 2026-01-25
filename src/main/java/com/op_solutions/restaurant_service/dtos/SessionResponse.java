package com.op_solutions.restaurant_service.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class SessionResponse {
	
	private UUID sessionId;
    private Long tableId;
    private LocalDateTime startedAt;

}
