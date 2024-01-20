package com.restApi.BasicRestApplication.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salary;

}
