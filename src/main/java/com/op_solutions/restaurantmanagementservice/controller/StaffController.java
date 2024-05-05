package com.op_solutions.restaurantmanagementservice.controller;

import com.op_solutions.restaurantmanagementservice.entity.Staff;
import com.op_solutions.restaurantmanagementservice.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/staffs")
@CrossOrigin("http://localhost:4200")
public class StaffController {

    private final StaffService staffService;

    @GetMapping("")
    public List<Staff> getStaffList()
    {
        return  staffService.getStaffList();
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<Staff> getStaffById(@PathVariable  Long staffId)
    {
        return  staffService.getStaffById(staffId);
    }


}
