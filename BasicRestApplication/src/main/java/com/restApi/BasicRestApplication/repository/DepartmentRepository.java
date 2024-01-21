package com.restApi.BasicRestApplication.repository;

import com.restApi.BasicRestApplication.dto.DepartmentSearchCriteriaDTO;
import com.restApi.BasicRestApplication.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query(value = """
            select dep from Department dep where (dep.departmentCode = :#{#criteria.departmentCode}) AND (dep.departmentName = :#{#criteria.departmentName}) AND (dep.departmentDescription = :#{#criteria.departmentDescription})
            """)
    Page<Department> getAllDepartmentsUsingPagination(
            @Param("criteria") DepartmentSearchCriteriaDTO departmentSearchCriteriaDTO,
            Pageable pageable);
}
