package com.restApi.BasicRestApplication.service;

import com.restApi.BasicRestApplication.dto.DepartmentDTO;
import com.restApi.BasicRestApplication.dto.DepartmentSearchCriteriaDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    DepartmentDTO addNewDepartment(DepartmentDTO departmentDTO);
    List<DepartmentDTO> getAllDepartments();
    DepartmentDTO getDepartmentByCode(String departmentCode);
    DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO);
    String deleteEmployee(Long departmentId);
    Page<DepartmentDTO> getAllDepartmentsUsingPagination(DepartmentSearchCriteriaDTO departmentSearchCriteriaDTO);
}
