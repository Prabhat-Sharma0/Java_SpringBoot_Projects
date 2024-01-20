package com.restApi.BasicRestApplication.service;

import com.restApi.BasicRestApplication.dto.DepartmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    DepartmentDTO addNewDepartment(DepartmentDTO departmentDTO);
    List<DepartmentDTO> getAllDepartments();
    DepartmentDTO getDepartmentByCode(String departmentCode);
    DepartmentDTO updateDepartment(String departmentCode, DepartmentDTO departmentDTO);
    String deleteEmployee(Long departmentId);
}
