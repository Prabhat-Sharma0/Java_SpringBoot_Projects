package com.restApi.BasicRestApplication.service.impl;

import com.restApi.BasicRestApplication.dto.EmployeeDTO;
import com.restApi.BasicRestApplication.entity.Employee;
import com.restApi.BasicRestApplication.mapper.EmployeeMapper;
import com.restApi.BasicRestApplication.repository.EmployeeRepository;
import com.restApi.BasicRestApplication.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private EmployeeMapper employeeMapper;

    public EmployeeDTO addEmployeeData(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.employeeDTOToEmployee(employeeDTO);

        if(employeeRepository.findAll().stream().noneMatch(
                emp -> emp.getEmail().equalsIgnoreCase(employee.getEmail()))) {
            employeeRepository.save(employee);
        }
        return employeeMapper.employeeToEmployeeDTO(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeDTO updateEmployee(String employeeId, EmployeeDTO employeeDTO) {
        employeeDTO.setId(employeeId);
        Employee emp = employeeMapper.employeeDTOToEmployee(employeeDTO);
        Employee updatedEmployee = employeeRepository.save(emp);

        return employeeMapper.employeeToEmployeeDTO(updatedEmployee);
    }

    public String deleteEmployee(String employeeId) {
        if (employeeRepository.findAll().stream().anyMatch(emp -> emp.getId().equals(employeeId))) {
            employeeRepository.delete(employeeRepository.findById(employeeId).orElseThrow());
            return "Employee Details Deleted";
        }

        return "No employee with such id is present in the database.";
    }

    public EmployeeDTO getEmployeeByEmail(String email) {
        if (employeeRepository.findAll().stream().anyMatch(emp -> emp.getEmail().equals(email))) {
            Employee employee = employeeRepository.findAll().stream().filter(
                    emp -> emp.getEmail().equals(email)).findAny().orElseThrow();
            return employeeMapper.employeeToEmployeeDTO(employee);
        }

        return null;
    }

}
