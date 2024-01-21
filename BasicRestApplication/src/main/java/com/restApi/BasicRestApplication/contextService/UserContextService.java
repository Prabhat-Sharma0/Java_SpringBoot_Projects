package com.restApi.BasicRestApplication.contextService;

import com.restApi.BasicRestApplication.exception.UserContextException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserContextService {
    String getUserEmail(HttpServletRequest request) throws UserContextException;
}
