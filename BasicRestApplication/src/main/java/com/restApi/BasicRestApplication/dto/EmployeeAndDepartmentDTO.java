package com.restApi.BasicRestApplication.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAndDepartmentDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salary;
    private DepartmentDTO departmentDTO;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DepartmentDTO {
        private Long id;
        private String departmentCode;
        private String departmentName;
        private String departmentDescription;
    }
}
