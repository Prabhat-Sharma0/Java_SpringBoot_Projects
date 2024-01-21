package com.restApi.BasicRestApplication.repository;

import com.restApi.BasicRestApplication.dto.EmployeeSearchCriteriaDTO;
import com.restApi.BasicRestApplication.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query(value = """
            select emp from Employee emp where (emp.firstName = :#{#criteria.firstName}) AND (emp.lastName = :#{#criteria.lastName}) AND (emp.email = :#{#criteria.email})
            """)
    Page<Employee> getAllEmployeesUsingPagination(
            @Param("criteria") EmployeeSearchCriteriaDTO employeeSearchCriteriaDTO,
            Pageable pageable);


    @EntityGraph(attributePaths = "department")
    @Query(value = """
            select emp from Employee emp where emp.email = :email
            """)
    Employee getEmployeeAndDepartmentByEmployeeEmail(@Param("email") String email);

    List<Employee> findByDepartmentId(Long departmentId);

}
