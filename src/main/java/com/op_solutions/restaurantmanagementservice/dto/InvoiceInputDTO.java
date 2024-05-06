package com.op_solutions.restaurantmanagementservice.dto;

import com.op_solutions.restaurantmanagementservice.entity.Order;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceInputDTO {

    private Long invoiceNumber;
    private Long orderId;
    private BigDecimal totalPrice;
    private String modeOfPayment; // Online - QR - Cash

}
