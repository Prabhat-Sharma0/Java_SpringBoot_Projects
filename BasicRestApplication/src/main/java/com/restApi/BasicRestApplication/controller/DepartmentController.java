package com.restApi.BasicRestApplication.controller;

import com.restApi.BasicRestApplication.dto.DepartmentDTO;
import com.restApi.BasicRestApplication.dto.DepartmentSearchCriteriaDTO;
import com.restApi.BasicRestApplication.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@AllArgsConstructor
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/department")
    public DepartmentDTO getEmployeeByEmail(@Param("departmentCode") String departmentCode) {
        return departmentService.getDepartmentByCode(departmentCode);
    }

    @PostMapping("/add")
    public DepartmentDTO addEmployee(@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.addNewDepartment(departmentDTO);
    }

    @PutMapping("/update/{departmentId}")
    public DepartmentDTO updateEmployee(@PathVariable("departmentId") Long departmentId, @RequestBody DepartmentDTO departmentDTO) {
        return departmentService.updateDepartment(departmentId, departmentDTO);
    }

    @DeleteMapping("/delete/{departmentId}")
    public String deleteEmployee(@PathVariable("departmentId") Long departmentId) {
        return departmentService.deleteEmployee(departmentId);
    }

    @PostMapping("/search")
    public Page<DepartmentDTO> getAllDepartmentsUsingPagination(@Valid @RequestBody DepartmentSearchCriteriaDTO departmentSearchCriteriaDTO) {
        return departmentService.getAllDepartmentsUsingPagination(departmentSearchCriteriaDTO);
    }

}
