package com.op_solutions.restaurantmanagementservice.service;

import com.op_solutions.restaurantmanagementservice.dto.InvoiceInputDTO;
import com.op_solutions.restaurantmanagementservice.entity.Invoice;
import com.op_solutions.restaurantmanagementservice.entity.ItemOrder;
import com.op_solutions.restaurantmanagementservice.entity.RestaurantTable;
import com.op_solutions.restaurantmanagementservice.mapper.InvoiceMapperUtil;
import com.op_solutions.restaurantmanagementservice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final RestaurantTableService restaurantTableService;

    private final InvoiceMapperUtil invoiceMapperUtil;


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


    public ResponseEntity<Invoice> saveInvoice(InvoiceInputDTO invoiceInputDTO)
    {
        if(invoiceInputDTO != null)
        {
            Invoice mappedInvoice = invoiceMapperUtil.mapInvoiceFromInvoiceInputDTO(invoiceInputDTO);

            //lets check if itemOrder kitchen status is completed
            List<ItemOrder> itemOrderList = mappedInvoice.getOrder().getItemsOrderedList().stream()
                    .filter( itemOrder -> itemOrder.getKitchenItemStatus().equals("PENDING") || itemOrder.getKitchenItemStatus().equals("INPROGRESS"))
                    .collect(Collectors.toList());

            if(itemOrderList != null && itemOrderList.size() > 0)
            {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            boolean invoiceSaved = false;
            RestaurantTable restaurantTable = null;
            Invoice saveInvoice = null;

            if(itemOrderList == null || itemOrderList.size() == 0)
            {
                saveInvoice = invoiceRepository.save(mappedInvoice);

                if(saveInvoice != null)
                {
                    invoiceSaved = true;
                    restaurantTable = saveInvoice.getOrder().getRestaurantTable();
                }
            }

            if(invoiceSaved && restaurantTable != null)
            {
                restaurantTable.setOccupancy("Vacant");
                restaurantTableService.saveRestaurantTable(restaurantTable);

            }

            // lets update the Restaurant table status to vacant


            return ResponseEntity.ok().body(saveInvoice);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


    }

    public ResponseEntity<Invoice> updateInvoice(Invoice invoice,Long invoiceNumber)
    {

        Optional<Invoice> invoiceDB = invoiceRepository.findById(invoiceNumber);

        if(invoiceDB.isPresent()) {
            Invoice updateInvoice = invoiceRepository.save(invoice);

            return ResponseEntity.ok().body(updateInvoice);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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

