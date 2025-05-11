package com.erick.interceptor;

import com.erick.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1. Get the path of the request
        // 2. assert if is a login request
        if (request.getRequestURI().equals("/login")) {
            // 3. if is a login request, continue
            log.info("Login Request, Continue");
            return true;
        }

        // 4. if is not a login request, get the token from the request header
        String token = request.getHeader("token");

        // 5.assert if the token exists. If not exists, user is not logged in, return error messages(401 status code)
        if (token == null || token.isEmpty()) {
            log.info("Token is null or empty, user is not logged in, return error messages(401 status code)");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // response.setStatus(401);
            return false;
        }

        // 6. if the token exists, check if the token is valid. If not valid, return error messages(401 status code)
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e){
            log.info("Token is invalid, return error messages(401 status code)");
            response.setStatus(401);
            return false;
        }
        // 7. if the token is valid, continue
        log.info("Token is valid, continue");
        return true;
    }
}
