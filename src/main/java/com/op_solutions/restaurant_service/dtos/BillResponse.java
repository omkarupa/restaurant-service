package com.op_solutions.restaurant_service.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.op_solutions.restaurant_service.models.PaymentStatus;

import lombok.Data;

@Data
public class BillResponse {

	private UUID billId;
    private UUID sessionId;
    private BigDecimal totalAmount;
    private PaymentStatus paymentStatus;
    private LocalDateTime generatedAt;
}
