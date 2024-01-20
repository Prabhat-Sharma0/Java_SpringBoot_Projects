package com.restApi.BasicRestApplication.mapper;

import com.restApi.BasicRestApplication.dto.EmployeeDTO;
import com.restApi.BasicRestApplication.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public final ModelMapper modelMapper = new ModelMapper();

    public EmployeeDTO employeeToEmployeeDTO(Employee employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public Employee employeeDTOToEmployee(EmployeeDTO employeeDTO) {
        return modelMapper.map(employeeDTO, Employee.class);
    }

}
