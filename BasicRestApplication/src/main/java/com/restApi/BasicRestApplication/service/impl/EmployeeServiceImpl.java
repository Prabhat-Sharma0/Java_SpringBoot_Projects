package com.restApi.BasicRestApplication.service.impl;

import com.restApi.BasicRestApplication.dto.EmployeeDTO;
import com.restApi.BasicRestApplication.entity.Department;
import com.restApi.BasicRestApplication.entity.Employee;
import com.restApi.BasicRestApplication.mapper.EmployeeMapper;
import com.restApi.BasicRestApplication.repository.DepartmentRepository;
import com.restApi.BasicRestApplication.repository.EmployeeRepository;
import com.restApi.BasicRestApplication.service.EmployeeService;
import com.restApi.BasicRestApplication.dto.EmployeeSearchCriteriaDTO;
import com.restApi.BasicRestApplication.utils.SortItem;
import com.restApi.BasicRestApplication.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    private EmployeeMapper employeeMapper;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeMapper.employeeToEmployeeDTO(employeeRepository.findAll());
    }

    public EmployeeDTO addEmployeeData(Long departmentId, EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.employeeDTOToEmployee(employeeDTO);
        Department department = departmentRepository.findById(departmentId).orElseThrow();

        if(employeeRepository.findAll().stream().noneMatch(
                emp -> emp.getEmail().equalsIgnoreCase(employee.getEmail()))) {
            employee.setDepartment(department);
            employeeRepository.save(employee);
        }
        return employeeMapper.employeeToEmployeeDTO(employee);
    }

    public EmployeeDTO updateEmployee(Long departmentId, String employeeId, EmployeeDTO employeeDTO) {
        Employee employeeFromDB = employeeRepository.findById(employeeId).orElseThrow();
        Department departmentFromDB = departmentRepository.findById(departmentId).orElseThrow();

        if (checkEmployeeBelongsToDepartment(departmentFromDB, employeeFromDB)) {
            return null;
        }

        employeeDTO.setId(employeeId);
        Employee updatedEmployee = employeeMapper.employeeDTOToEmployee(employeeDTO);
        updatedEmployee.setDepartment(departmentFromDB);
        Employee savedEmployee = employeeRepository.save(updatedEmployee);

        return employeeMapper.employeeToEmployeeDTO(savedEmployee);
    }

    public String deleteEmployee(Long departmentId, String employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        Department department = departmentRepository.findById(departmentId).orElseThrow();

        if (checkEmployeeBelongsToDepartment(department, employee)) {
            return "employee not belongs to department";
        }

        if (employeeRepository.findAll().stream().anyMatch(emp -> emp.getId().equals(employeeId))) {
            employeeRepository.delete(employeeRepository.findById(employeeId).orElseThrow());
            return "Employee details deleted";
        }

        return "No employee with such id is present in the database.";
    }

    public EmployeeDTO getEmployeeByEmail(String email) {
        if (employeeRepository.findAll().stream().anyMatch(emp -> emp.getEmail().equals(email))) {
            Employee employee = employeeRepository.findAll().stream().filter(
                    emp -> emp.getEmail().equals(email)).findAny().orElseThrow();
            return employeeMapper.employeeToEmployeeDTO(employee);
        }

        return null;
    }

    public List<EmployeeDTO> getEmployeesByDepartmentId(Long departmentId) {
        List<Employee> employeeList = employeeRepository.findByDepartmentId(departmentId);
        return employeeMapper.employeeToEmployeeDTO(employeeList);
    }

    public Page<EmployeeDTO> getEmployeesByPagination(EmployeeSearchCriteriaDTO employeeSearchCriteriaDTO) {
        Integer page = employeeSearchCriteriaDTO.getPage();
        Integer size = employeeSearchCriteriaDTO.getSize();
        List<SortItem> sortItems = employeeSearchCriteriaDTO.getSortList();

        Pageable pageable = Utils.createPageableBasedOnPageAndSizeAndSorting(page, size, sortItems);
        Page<Employee> employeesFromDb = employeeRepository.getAllEmployeesUsingPagination(employeeSearchCriteriaDTO, pageable);

        List<EmployeeDTO> employeeDTOS = employeeMapper.employeeToEmployeeDTO(employeesFromDb.getContent());

        return new PageImpl<>(employeeDTOS, pageable, employeesFromDb.getTotalElements());
    }

    private boolean checkEmployeeBelongsToDepartment(Department department, Employee employee) {
        if (department == null || employee == null) return true;

        Long departmentId = employee.getDepartment().getId();
        return !(departmentId != null & department.getId().equals(departmentId));
    }

}
