package com.restApi.BasicRestApplication.mapper;

import com.restApi.BasicRestApplication.dto.DepartmentDTO;
import com.restApi.BasicRestApplication.entity.Department;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public final ModelMapper modelMapper = new ModelMapper();

    public DepartmentDTO departmentToDepartmentDTO(Department department) {
        return modelMapper.map(department, DepartmentDTO.class);
    }

    public Department departmentDTOToDepartment(DepartmentDTO departmentDTO) {
        return modelMapper.map(departmentDTO, Department.class);
    }

}
