package com.op_solutions.restaurantmanagementservice.controller;

import com.op_solutions.restaurantmanagementservice.entity.Invoice;
import com.op_solutions.restaurantmanagementservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class InvoiceController {


    private final InvoiceService invoiceService;


    @GetMapping("/{invoiceNumber}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long invoiceNumber)
    {
        return invoiceService.getInvoiceById(invoiceNumber);
    }

    @GetMapping("")
    public List<Invoice> getInvoiceList()
    {
        return invoiceService.getInvoiceList();
    }

    @PostMapping("/invoice")
    public ResponseEntity<Invoice> saveInvoice(@RequestBody Invoice invoice)
    {
        return null;
    }

    @PutMapping("/{invoiceNumber}")
    public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice,@PathVariable Long invoiceNumber)
    {
        return null;
    }

    @DeleteMapping("/{invoiceNumber}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable Long invoiceNumber)
    {
        return null;
    }


}


