package com.ems.services;

import com.ems.dtos.EmployeeDTO;

import java.util.List;

public interface EmployeeServices {

    EmployeeDTO createEmployee(EmployeeDTO empDto);
    EmployeeDTO getEmployeeById(Long id);
     List<EmployeeDTO> getAllEmployees();
     EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

     EmployeeDTO deleteEmployee(Long id);

}