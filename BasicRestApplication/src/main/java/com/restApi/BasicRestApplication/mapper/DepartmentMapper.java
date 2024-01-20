package com.restApi.BasicRestApplication.mapper;

import com.restApi.BasicRestApplication.dto.DepartmentDTO;
import com.restApi.BasicRestApplication.entity.Department;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentMapper {

    public final ModelMapper modelMapper = new ModelMapper();

    public DepartmentDTO departmentToDepartmentDTO(Department department) {
        return modelMapper.map(department, DepartmentDTO.class);
    }

    public List<DepartmentDTO> departmentToDepartmentDTO(List<Department> departments) {
        return departments.stream().map(department -> modelMapper.map(department, DepartmentDTO.class)).toList();
    }

    public Department departmentDTOToDepartment(DepartmentDTO departmentDTO) {
        return modelMapper.map(departmentDTO, Department.class);
    }

    public List<Department> departmentDTOToDepartment(List<DepartmentDTO> departmentDTOS) {
        return departmentDTOS.stream().map(departmentDTO -> modelMapper.map(departmentDTO, Department.class)).toList();
    }

}
