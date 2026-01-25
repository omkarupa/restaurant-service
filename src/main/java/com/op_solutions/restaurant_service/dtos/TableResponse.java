package com.op_solutions.restaurant_service.dtos;

import com.op_solutions.restaurant_service.models.TableStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableResponse {

	private Long tableId;
    private int tableNumber;
    private TableStatus status;
}
