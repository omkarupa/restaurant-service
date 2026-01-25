package com.op_solutions.restaurant_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.op_solutions.restaurant_service.dtos.TableResponse;
import com.op_solutions.restaurant_service.models.TableStatus;
import com.op_solutions.restaurant_service.service.TableService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tables")
public class TableController {
	
	
	private final TableService tableService;
	
	  @GetMapping
	    public List<TableResponse> getAllTables() {
	        return tableService.getAllTables();
	    }

	    @PutMapping("/{tableId}/status")
	    public void updateTableStatus(
	            @PathVariable Long tableId,
	            @RequestParam TableStatus status) {
	        tableService.updateStatus(tableId, status);
	    }
	

}
