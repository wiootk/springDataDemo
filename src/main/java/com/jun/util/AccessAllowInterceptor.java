package com.jun.util;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Permission;

@Component
public class AccessAllowInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            HandlerMethod method = (HandlerMethod) handler;
//            Domain domain = method.getMethod().getDeclaringClass().getAnnotation(Domain.class);
            Domain domain =  AnnotationUtils.findAnnotation(method.getBeanType(), Domain.class);
            AccessAllow allow = method.getMethodAnnotation(AccessAllow.class);
            if (allow == null) {
                allow = AnnotationUtils.findAnnotation(method.getBeanType(), AccessAllow.class);
            }
            if(domain!=null){
                System.out.println("domain    "+JSON.toJSONString(domain.value()));
            }
            if(domain!=null){
                System.out.println("allow    "+JSON.toJSONString(allow.value()));
            }
        }
        return true;
    }
}