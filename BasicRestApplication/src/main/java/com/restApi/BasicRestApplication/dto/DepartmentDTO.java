package com.restApi.BasicRestApplication.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;
    private String departmentCode;
    private String departmentName;
    private String departmentDescription;
//    private Set<EmployeeDTO> employees;
}
