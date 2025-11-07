package com.example.delahuerta.filters;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.delahuerta.model.RequestLog;
import com.example.delahuerta.repository.RequestLogRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class RequestLoggingFilter implements Filter {
    @Autowired private RequestLogRepository logRepository;

    @Override
    public void doFilter(ServletRequest request,
    ServletResponse response, FilterChain chain)

    throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        RequestLog log = new RequestLog();
        log.setMethod(req.getMethod());
        log.setPath(req.getRequestURI());
        log.setIp(req.getRemoteAddr());
        log.setTimestamp(LocalDateTime.now());

        logRepository.save(log); // persistir en la base de datos
        chain.doFilter(request, response);
    }
}
