package com.ecommerce.ecommercebe.configuration.filter;

import com.ecommerce.ecommercebe.exception.InvalidTokenException;
import com.ecommerce.ecommercebe.pojo.response.CommonResponse;
import com.ecommerce.ecommercebe.pojo.response.JwtTokenResponse;
import com.ecommerce.ecommercebe.service.JWTTokenService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class RequestFilter implements Filter {
    @Autowired
    private JWTTokenService jwtTokenService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)  servletResponse;

        String token = request.getHeader("token");
        try{
            jwtTokenService.verifyToken(token);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e){
            String obj = "{ \"Code\": 400, \"Message\": \"Invalid token\"}";
            Gson gson = new Gson();
            response.setStatus(204);
            response.setContentType(gson.toJson(obj));
            return;
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
