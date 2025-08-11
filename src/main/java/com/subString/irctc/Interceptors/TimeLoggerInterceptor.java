package com.subString.irctc.Interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TimeLoggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       // request.getSession(true).setAttribute("startTime", System.currentTimeMillis());
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        System.out.println("TimeLoggerInterceptor preHandle");
        return true;
     }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("TimeLoggerInterceptor postHandle");

        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();

        long totalTime = endTime - startTime;
        System.out.println("Execution time: " + request.getRequestURI() + " : " + totalTime + " ms");

    }
}
