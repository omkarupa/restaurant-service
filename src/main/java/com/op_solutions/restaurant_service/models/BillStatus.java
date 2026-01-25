package com.op_solutions.restaurant_service.models;

public enum BillStatus {
	
	 OPEN,        // Items can be added
	    GENERATED,   // Finalized, awaiting payment
	    PAID,        // Payment completed
	    CANCELLED

}
