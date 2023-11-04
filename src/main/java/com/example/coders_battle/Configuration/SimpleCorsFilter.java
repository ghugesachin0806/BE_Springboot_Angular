package com.example.coders_battle.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

    private final String clientAppUrl = "http://localhost:4200/*";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response2 = (HttpServletResponse) response;
        HttpServletRequest request2 = (HttpServletRequest) request;
        Map<String,String> map = new HashMap<>();

        String originHeader = request2.getHeader("origin");
        response2.setHeader("Access-Control-Allow-Origin", originHeader);
        response2.setHeader("Access-control-Allow-Methods", "POST , GET , PUT , DELETE");
        response2.setHeader("Access-control-Max-Age", "3600");
        response2.setHeader("Access-control-Allow-Headers", "*");

        if("OPTIONS".equalsIgnoreCase(request2.getMethod()))
        {
            response2.setStatus(HttpServletResponse.SC_OK);
        } else{

            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
