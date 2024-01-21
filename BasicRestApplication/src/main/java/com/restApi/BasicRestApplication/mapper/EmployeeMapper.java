package com.restApi.BasicRestApplication.mapper;

import com.restApi.BasicRestApplication.dto.EmployeeAndDepartmentDTO;
import com.restApi.BasicRestApplication.dto.EmployeeDTO;
import com.restApi.BasicRestApplication.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {
    public final ModelMapper modelMapper = new ModelMapper();

    public EmployeeDTO employeeToEmployeeDTO(Employee employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public List<EmployeeDTO> employeeToEmployeeDTO(List<Employee> employees) {
        return employees.stream().map(emp -> modelMapper.map(emp, EmployeeDTO.class)).toList();
    }

    public Employee employeeDTOToEmployee(EmployeeDTO employeeDTO) {
        return modelMapper.map(employeeDTO, Employee.class);
    }

    public List<Employee> employeeDTOToEmployee(List<EmployeeDTO> employeeDTOS) {
        return employeeDTOS.stream().map(empDto -> modelMapper.map(empDto, Employee.class)).toList();
    }

    public EmployeeAndDepartmentDTO employeeToEmployeeAndDepartmentDTO(Employee employee) {
        EmployeeAndDepartmentDTO employeeAndDepartmentDTO = modelMapper.map(employee, EmployeeAndDepartmentDTO.class);
        employeeAndDepartmentDTO.setDepartmentDTO(modelMapper.map(employee.getDepartment(), EmployeeAndDepartmentDTO.DepartmentDTO.class));
        return employeeAndDepartmentDTO;
    }

    public List<EmployeeAndDepartmentDTO> employeesToEmployeeAndDepartmentDTOs(List<Employee> employees) {
        return employees.stream().map(employee -> {
            EmployeeAndDepartmentDTO employeeAndDepartmentDTO = modelMapper.map(employee, EmployeeAndDepartmentDTO.class);
            employeeAndDepartmentDTO.setDepartmentDTO(modelMapper.map(employee.getDepartment(), EmployeeAndDepartmentDTO.DepartmentDTO.class));
            return employeeAndDepartmentDTO;
        }).toList();
    }

}
