package com.op_solutions.restaurantmanagementservice.service;

import com.op_solutions.restaurantmanagementservice.entity.Invoice;
import com.op_solutions.restaurantmanagementservice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;



    public ResponseEntity<Invoice> getInvoiceById(Long invoiceNumber)
    {
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceNumber);

        if(invoice.isPresent())
        {
            return ResponseEntity.ok().body(invoice.get());
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public Optional<Invoice> getOptionalInvoiceById(Long invoiceNumber)
    {
        return invoiceRepository.findById(invoiceNumber);
    }


    public List<Invoice> getInvoiceList()
    {
        return invoiceRepository.findAll();
    }


    public ResponseEntity<Invoice> saveInvoice(Invoice invoice)
    {
        Invoice saveInvoice = invoiceRepository.save(invoice);

        return ResponseEntity.ok().body(saveInvoice);

    }

    public ResponseEntity<Invoice> updateInvoice(Invoice invoice,Long invoiceNumber)
    {

        Optional<Invoice> invoiceDB = invoiceRepository.findById(invoiceNumber);

        if(invoiceDB.isPresent())
        {
            Invoice updateInvoice = invoiceRepository.save(invoice);

            return ResponseEntity.ok().body(updateInvoice);
        }
        else {
            return saveInvoice(invoice);
        }

    }

    public ResponseEntity<Invoice> deleteInvoice(Long invoiceNumber)
    {
        Optional<Invoice> invoiceDB = invoiceRepository.findById(invoiceNumber);

        if(invoiceDB.isPresent())
        {
            invoiceRepository.deleteById(invoiceNumber);

            return ResponseEntity.ok().body(invoiceDB.get());
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}

