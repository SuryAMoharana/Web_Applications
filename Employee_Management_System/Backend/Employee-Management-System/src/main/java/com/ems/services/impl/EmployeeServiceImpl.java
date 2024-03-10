package com.ems.services.impl;

import com.ems.dtos.EmployeeDTO;
import com.ems.entities.Employee;
import com.ems.exceptions.ResourceNotFoundException;
import com.ems.repositories.EmployeeRepo;
import com.ems.services.EmployeeServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeServices {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO empDto) {
        return modelMapper.map(employeeRepo.save(modelMapper.map(empDto, Employee.class)), EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee=employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with id "+id));
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees=employeeRepo.findAll();
        List<EmployeeDTO> employeeDTOs=employees.stream().map((employee)->modelMapper.map(employee,EmployeeDTO.class)).toList();
        return employeeDTOs;
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee=employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with id "+id));
        employee.setEmail(employeeDTO.getEmail());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        Employee savedEmployee=employeeRepo.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO deleteEmployee(Long id) {
        Employee employee=employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with id "+id));
        employeeRepo.delete(employee);
        return modelMapper.map(employee, EmployeeDTO.class);
    }
}
