package com.b2x2.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static java.lang.System.currentTimeMillis;

@Component
@Order(2)
@Slf4j
public class RequestResponseLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        long startTime = currentTimeMillis();

        try {
            chain.doFilter(request, response);
            long elapsedTime = currentTimeMillis() - startTime;
            log.info(getResponseLog(request, response, elapsedTime));
        } catch (Throwable throwable) {
            long elapsedTime = currentTimeMillis() - startTime;
            log.warn(getErrorLog(request, throwable, elapsedTime));
            throw throwable;
        }
    }

    private String getResponseLog(HttpServletRequest request, HttpServletResponse response, long elapsedTime) {
        StringBuilder responseLogger = new StringBuilder();
        responseLogger.append(request.getProtocol());
        responseLogger.append(' ');
        responseLogger.append(response.getStatus());
        responseLogger.append(' ');
        responseLogger.append(request.getRemoteAddr());
        responseLogger.append(' ');
        responseLogger.append(request.getMethod());
        responseLogger.append(' ');
        responseLogger.append(request.getRequestURI());

        if (request.getQueryString() != null) {
            responseLogger.append('?');
            responseLogger.append(request.getQueryString());
        }

        if (response.getContentType() != null) {
            responseLogger.append(' ');
            responseLogger.append(response.getContentType());
        }

        responseLogger.append(' ');
        responseLogger.append(elapsedTime);
        responseLogger.append("ms");
        return responseLogger.toString();
    }

    private String getErrorLog(HttpServletRequest request, Throwable throwable, long elapsedTime) {
        StringBuilder errorLogger = new StringBuilder();
        errorLogger.append(request.getProtocol());
        errorLogger.append(" EXCEPTION ");
        errorLogger.append(throwable.getClass().getSimpleName());
        errorLogger.append(": ");
        errorLogger.append(throwable.getMessage());
        errorLogger.append(' ');
        errorLogger.append(request.getRemoteAddr());
        errorLogger.append(' ');
        errorLogger.append(request.getMethod());
        errorLogger.append(' ');
        errorLogger.append(request.getRequestURI());

        if (request.getQueryString() != null) {
            errorLogger.append('?');
            errorLogger.append(request.getQueryString());
        }

        errorLogger.append(' ');
        errorLogger.append(elapsedTime);
        errorLogger.append("ms");
        return errorLogger.toString();
    }
}