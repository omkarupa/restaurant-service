package com.op_solutions.restaurantmanagementservice.mapper;

import com.op_solutions.restaurantmanagementservice.dto.InvoiceInputDTO;
import com.op_solutions.restaurantmanagementservice.entity.Invoice;
import com.op_solutions.restaurantmanagementservice.entity.Order;
import com.op_solutions.restaurantmanagementservice.service.InvoiceService;
import com.op_solutions.restaurantmanagementservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
@RequiredArgsConstructor
public class InvoiceMapperUtil {

    private final OrderService orderService;

    public Invoice mapInvoiceFromInvoiceInputDTO(InvoiceInputDTO invoiceInputDTO)
    {
        Order order = orderService.getOrderById(invoiceInputDTO.getOrderId()).getBody();
        BigDecimal totalPrice = order.getTotalPrice();
        String modeOfPayment = invoiceInputDTO.getModeOfPayment();

        Invoice invoice = Invoice.builder()
                .order(order)
                .totalPrice(totalPrice)
                .modeOfPayment(modeOfPayment)
                .build();
        return invoice;

    }
}
