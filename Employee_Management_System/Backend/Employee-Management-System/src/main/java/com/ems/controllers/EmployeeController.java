package com.ems.controllers;

import com.ems.dtos.EmployeeDTO;
import com.ems.services.EmployeeServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "EmployeeController", description = "APIs for Employee Management")
public class EmployeeController {
    @Autowired
    private EmployeeServices employeeServices;

    //Build ADD employee REST API
    @PostMapping("/create")
    @Operation(summary = "Create New user")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<EmployeeDTO> createEmployeeHandler(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO savedEmployee=employeeServices.createEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeByIdHandler(@PathVariable("id") Long empId){
        EmployeeDTO employeeDTO=employeeServices.getEmployeeById(empId);
        return ResponseEntity.ok(employeeDTO);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeeHandler(){
        List<EmployeeDTO> employeeDTOs=employeeServices.getAllEmployees();
        return ResponseEntity.ok(employeeDTOs);
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeeHandler(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO updatedEmployee=employeeServices.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployeeHandler(@PathVariable Long id){
        EmployeeDTO employeeDTO=employeeServices.deleteEmployee(id);
        return ResponseEntity.ok(employeeDTO);
    }
}
