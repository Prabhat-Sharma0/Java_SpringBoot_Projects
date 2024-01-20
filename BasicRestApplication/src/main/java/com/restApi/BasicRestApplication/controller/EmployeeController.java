package com.restApi.BasicRestApplication.controller;

import com.restApi.BasicRestApplication.dto.EmployeeDTO;
import com.restApi.BasicRestApplication.entity.Employee;
import com.restApi.BasicRestApplication.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public List<Employee> getAllEmployeeData() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee")
    public EmployeeDTO getEmployeeByEmail(@Param("email") String email) {
        return employeeService.getEmployeeByEmail(email);
    }

    @PostMapping("/add")
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.addEmployeeData(employeeDTO);
    }

    @PutMapping("/update/{employeeId}")
    public EmployeeDTO updateEmployee(@PathVariable("employeeId") String employeeId, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(employeeId, employeeDTO);
    }

    @DeleteMapping("/delete/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") String employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

}
