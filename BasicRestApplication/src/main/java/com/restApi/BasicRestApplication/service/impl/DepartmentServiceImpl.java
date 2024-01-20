package com.restApi.BasicRestApplication.service.impl;

import com.restApi.BasicRestApplication.dto.DepartmentDTO;
import com.restApi.BasicRestApplication.entity.Department;
import com.restApi.BasicRestApplication.mapper.DepartmentMapper;
import com.restApi.BasicRestApplication.repository.DepartmentRepository;
import com.restApi.BasicRestApplication.service.DepartmentService;
import lombok.AllArgsConstructor;
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

}
