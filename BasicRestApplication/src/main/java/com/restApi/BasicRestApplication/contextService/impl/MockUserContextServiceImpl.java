package com.restApi.BasicRestApplication.contextService.impl;

import com.restApi.BasicRestApplication.contextService.UserContextService;
import com.restApi.BasicRestApplication.dto.EmployeeAndDepartmentDTO;
import com.restApi.BasicRestApplication.exception.UserContextException;
import com.restApi.BasicRestApplication.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("mockContext")
public class MockUserContextServiceImpl implements UserContextService {

    @Value("${system.user}")
    private String userEmail;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public String getUserEmail(HttpServletRequest request) throws UserContextException {
        if(userEmail == null || userEmail.isEmpty()) {
            throw new UserContextException("The property {system.user} is not set at properties");
        }

        EmployeeAndDepartmentDTO employeeAndDepartmentDTO = employeeService.getEmployeeAndDepartmentByEmployeeEmail(userEmail);

        if (!(employeeAndDepartmentDTO.getDepartmentDTO().getId() >= 3)) {
            throw new UserContextException("The user is not authorized for accessing this service operation");
        }

        System.out.println("User login for application: " + userEmail);
        return userEmail;
    }
}
