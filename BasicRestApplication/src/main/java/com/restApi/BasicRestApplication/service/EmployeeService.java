package com.restApi.BasicRestApplication.service;

import com.restApi.BasicRestApplication.dto.EmployeeDTO;
import com.restApi.BasicRestApplication.dto.EmployeeSearchCriteriaDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    EmployeeDTO addEmployeeData(Long departmentId, EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeByEmail(String email);
    EmployeeDTO updateEmployee(Long departmentId, String employeeId, EmployeeDTO employeeDTO);
    String deleteEmployee(Long departmentId, String employeeId);
    List<EmployeeDTO> getEmployeesByDepartmentId(Long departmentId);
    Page<EmployeeDTO> getEmployeesByPagination(EmployeeSearchCriteriaDTO employeeSearchCriteriaDTO);
}
