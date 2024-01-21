package com.restApi.BasicRestApplication.aspect;

import com.restApi.BasicRestApplication.contextService.UserContextService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class UserTokenSaver {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserContextService userContextService;

    @Pointcut("execution(* comcom.restApi.BasicRestApplication.controller.DepartmentController.*(..))")
    public void departmentControllerExecutionPointcut() {
    //  pointcut definition for calling Department Execution
    }

    @Around("(execution(* com.restApi.BasicRestApplication.controller.DepartmentController.*(..)))")
    public Object saveToken(ProceedingJoinPoint joinPoint) {
        try {
            final String email = userContextService.getUserEmail(httpServletRequest);
            log.info("User checking the department data: " + email);
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
