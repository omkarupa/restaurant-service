package com.op_solutions.restaurant_service.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.op_solutions.restaurant_service.dtos.BillResponse;
import com.op_solutions.restaurant_service.dtos.PaymentRequest;
import com.op_solutions.restaurant_service.exception.BusinessException;
import com.op_solutions.restaurant_service.models.Bill;
import com.op_solutions.restaurant_service.service.BillingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bill")
public class BillingController {

    private final BillingService billingService;
    
    @GetMapping("/{sessionId}")
    public Bill getBill(@PathVariable UUID sessionId)
    {
    	
		 if(billingService.getBillBySessionId(sessionId) == null)
		 {
			 throw new BusinessException("Bill not available for session");
		 }
		 
		 return billingService.getBillBySessionId(sessionId);
    	
    }

   
    // Customer asks for bill
    @PostMapping("/generate")
    public BillResponse generateBill(@RequestParam UUID sessionId) {
        return billingService.generateBill(sessionId);
    }

    // Payment (cash / online)
    @PostMapping("/pay")
    public void payBill(@RequestBody PaymentRequest request) {
    	
    	 if(billingService.getBillBySessionId(request.getSessionId()) == null)
		 {
			 throw new BusinessException("Bill not available for session");
		 }
    	 
        billingService.pay(request);
    }
}
