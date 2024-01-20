package com.restApi.BasicRestApplication.service;

import com.restApi.BasicRestApplication.dto.EmployeeDTO;
import com.restApi.BasicRestApplication.entity.Employee;
import com.restApi.BasicRestApplication.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    EmployeeDTO addEmployeeData(EmployeeDTO employeeDTO);
    List<Employee> getAllEmployees();
    EmployeeDTO getEmployeeByEmail(String email);
    EmployeeDTO updateEmployee(String employeeId, EmployeeDTO employeeDTO);
    String deleteEmployee(String employeeId);
}
