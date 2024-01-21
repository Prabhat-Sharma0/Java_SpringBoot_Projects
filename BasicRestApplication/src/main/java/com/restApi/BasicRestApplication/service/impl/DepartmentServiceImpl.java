package com.restApi.BasicRestApplication.service.impl;

import com.restApi.BasicRestApplication.dto.DepartmentDTO;
import com.restApi.BasicRestApplication.dto.DepartmentSearchCriteriaDTO;
import com.restApi.BasicRestApplication.entity.Department;
import com.restApi.BasicRestApplication.mapper.DepartmentMapper;
import com.restApi.BasicRestApplication.repository.DepartmentRepository;
import com.restApi.BasicRestApplication.service.DepartmentService;
import com.restApi.BasicRestApplication.utils.SortItem;
import com.restApi.BasicRestApplication.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private DepartmentMapper departmentMapper;

    public List<DepartmentDTO> getAllDepartments() {
        return departmentMapper.departmentToDepartmentDTO(departmentRepository.findAll());
    }

    @Override
    public DepartmentDTO addNewDepartment(DepartmentDTO departmentDTO) {
        Department department = departmentMapper.departmentDTOToDepartment(departmentDTO);

        if(departmentRepository.findAll().stream().noneMatch(
                dep -> dep.getDepartmentCode().equals(departmentDTO.getDepartmentCode()))) {
            departmentRepository.save(department);
        }

        return departmentMapper.departmentToDepartmentDTO(department);
    }

    @Override
    public DepartmentDTO getDepartmentByCode(String departmentCode) {
        return departmentMapper.departmentToDepartmentDTO(departmentRepository.findAll().stream().filter(
                dep -> dep.getDepartmentCode().equals(departmentCode)).findAny().orElse(null));
    }

    @Override
    public DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO) {
        Department departmentFromDb = departmentRepository.findById(departmentId).orElseThrow();

        departmentDTO.setId(departmentId);
        Department updatedDepartment = departmentMapper.departmentDTOToDepartment(departmentDTO);
        updatedDepartment.setEmployees(departmentFromDb.getEmployees());
        Department savedDepartment = departmentRepository.save(updatedDepartment);

        return departmentMapper.departmentToDepartmentDTO(savedDepartment);
    }

    @Override
    public String deleteEmployee(Long departmentId) {
        if (departmentRepository.findAll().stream().anyMatch(dep -> Objects.equals(dep.getId(), departmentId))) {
            departmentRepository.delete(departmentRepository.findById(departmentId).orElseThrow());
            return "Department details deleted";
        }

        return "No department with such id is present in the database.";
    }

    @Override
    public Page<DepartmentDTO> getAllDepartmentsUsingPagination(DepartmentSearchCriteriaDTO departmentSearchCriteriaDTO) {

        Integer page = departmentSearchCriteriaDTO.getPage();
        Integer size = departmentSearchCriteriaDTO.getSize();
        List<SortItem> sortList = departmentSearchCriteriaDTO.getSortList();

        Pageable pageable = Utils.createPageableBasedOnPageAndSizeAndSorting(page, size, sortList);
        Page<Department> recordsFromDb = departmentRepository.getAllDepartmentsUsingPagination(departmentSearchCriteriaDTO, pageable);

        List<DepartmentDTO> result = departmentMapper.departmentToDepartmentDTO(recordsFromDb.getContent());

        return new PageImpl<>(result, pageable, recordsFromDb.getTotalElements());
    }



}
