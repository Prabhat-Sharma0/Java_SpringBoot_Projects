package com.restApi.BasicRestApplication.controller;

import com.restApi.BasicRestApplication.dto.EmployeeDTO;
import com.restApi.BasicRestApplication.dto.EmployeeSearchCriteriaDTO;
import com.restApi.BasicRestApplication.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public List<EmployeeDTO> getAllEmployeeData() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee")
    public EmployeeDTO getEmployeeByEmail(@Param("email") String email) {
        return employeeService.getEmployeeByEmail(email);
    }

    @PostMapping("/add")
    public EmployeeDTO addEmployee(@Param("departmentId") Long departmentId, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.addEmployeeData(departmentId, employeeDTO);
    }

    @PutMapping("/update/{employeeId}")
    public EmployeeDTO updateEmployee(@Param("departmentId") Long departmentId, @PathVariable("employeeId") String employeeId, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(departmentId, employeeId, employeeDTO);
    }

    @DeleteMapping("/delete/{employeeId}")
    public String deleteEmployee(@Param("departmentId") Long departmentId, @PathVariable("employeeId") String employeeId) {
        return employeeService.deleteEmployee(departmentId, employeeId);
    }

    @GetMapping("/department/{departmentId}")
    public List<EmployeeDTO> getEmployeesOfDepartment(@PathVariable("departmentId") Long departmentId) {
        return employeeService.getEmployeesByDepartmentId(departmentId);
    }

    @PostMapping("/search")
    public Page<EmployeeDTO> getAllEmployeesUsingPagination(@Valid @RequestBody EmployeeSearchCriteriaDTO employeeSearchCriteriaDTO) {
        return employeeService.getEmployeesByPagination(employeeSearchCriteriaDTO);
    }

}
