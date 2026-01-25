package com.op_solutions.restaurant_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op_solutions.restaurant_service.dtos.TableResponse;
import com.op_solutions.restaurant_service.exception.ResourceNotFoundException;
import com.op_solutions.restaurant_service.models.Table;
import com.op_solutions.restaurant_service.models.TableStatus;
import com.op_solutions.restaurant_service.repository.TableRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TableService {
	
	private final TableRepository tableRespository;
	
	

	public List<TableResponse> getAllTables() {
		return tableRespository.findAll().stream().map(this::toResponse).toList();
	}

	public void updateStatus(Long tableId, TableStatus status) {
		Table table = tableRespository.findById(tableId).orElseThrow(() -> new ResourceNotFoundException("Table Not found"));
		
		table.setStatus(status);
		tableRespository.save(table);
		
	}
	
	public TableResponse toResponse(Table table)
	{
		return TableResponse.builder()
				.tableId(table.getId())
				.tableNumber(table.getTableNumber())
				.status(table.getStatus())
				.build();
	}

}
