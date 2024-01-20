package com.restApi.BasicRestApplication.service;

import com.restApi.BasicRestApplication.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    EmployeeDTO addEmployeeData(EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeByEmail(String email);
    EmployeeDTO updateEmployee(String employeeId, EmployeeDTO employeeDTO);
    String deleteEmployee(String employeeId);
}
