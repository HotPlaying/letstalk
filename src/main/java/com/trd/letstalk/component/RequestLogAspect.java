//package com.trd.letstalk.component;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.http.HttpServletRequest;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Slf4j
//@Aspect
//@Component
//public class RequestLogAspect {
//    @Pointcut("execution(public * com.main.letstalk.controller..*.*(..))")
//    public void webLog(){}
//
//    @Before("webLog()")
//    public void doBefore(JoinPoint joinPoint) {
//        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        log.info(" #URL : " + request.getRequestURL());
//        log.info(" #IP : " + request.getRemoteAddr());
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "webLog()")
//    public void doAfterReturning(Object ret) {
//        // 处理完请求，返回内容
//        log.info(" #RESPONSE : " + ret);
//    }
//}
