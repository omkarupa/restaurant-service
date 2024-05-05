package com.op_solutions.restaurantmanagementservice.service;

import com.op_solutions.restaurantmanagementservice.entity.Staff;
import com.op_solutions.restaurantmanagementservice.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;



    public ResponseEntity<Staff> getStaffById(Long staffId)
    {
        Optional<Staff> staff = staffRepository.findById(staffId);

        if(staff.isPresent())
        {
            return ResponseEntity.ok().body(staff.get());
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public Optional<Staff> getOptionalStaffById(Long staffId)
    {
        return staffRepository.findById(staffId);
    }


    public List<Staff> getStaffList()
    {
        return staffRepository.findAll();
    }


    public ResponseEntity<Staff> saveStaff(Staff staff)
    {
        Staff saveStaff = staffRepository.save(staff);

        return ResponseEntity.ok().body(saveStaff);

    }

    public ResponseEntity<Staff> updateStaff(Staff staff,Long staffId)
    {

        Optional<Staff> staffDB = staffRepository.findById(staffId);

        if(staffDB.isPresent())
        {
            Staff updateStaff = staffRepository.save(staff);

            return ResponseEntity.ok().body(updateStaff);
        }
        else {
            return saveStaff(staff);
        }

    }

    public ResponseEntity<Staff> deleteStaff(Long staffId)
    {
        Optional<Staff> staffDB = staffRepository.findById(staffId);

        if(staffDB.isPresent())
        {
            staffRepository.deleteById(staffId);

            return ResponseEntity.ok().body(staffDB.get());
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
