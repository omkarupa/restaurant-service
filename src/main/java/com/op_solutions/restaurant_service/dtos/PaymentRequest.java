package com.op_solutions.restaurant_service.dtos;

import java.util.UUID;

import com.op_solutions.restaurant_service.models.PaymentMethod;

import lombok.Data;
@Data
public class PaymentRequest {

	 private UUID sessionId;
	 private PaymentMethod paymentMethod;
}
