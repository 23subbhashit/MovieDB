package com.example.moviedb.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestTimingInterceptor implements HandlerInterceptor {

    private static final String START_TIME =
            "requestStartTime";

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {

        // Store request start time
        request.setAttribute(
                START_TIME,
                System.currentTimeMillis()
        );

        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception exception
    ) {

        Long startTime =
                (Long) request.getAttribute(START_TIME);

        long duration =
                System.currentTimeMillis() - startTime;

        System.out.println(
                "Request: "
                        + request.getMethod()
                        + " "
                        + request.getRequestURI()
                        + " | Status: "
                        + response.getStatus()
                        + " | Time: "
                        + duration
                        + " ms"
        );
    }
}