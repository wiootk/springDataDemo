package com.jun.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

public class DataSourceAspect {
    public void before(JoinPoint point)      {
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?>[] classz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                .getMethod().getParameterTypes();
        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            if(m != null){
                if(m.isAnnotationPresent(DataSource.class)){
                    DataSource data = m.getAnnotation(DataSource.class);
                    DynamicDataSourceHolder.setDataSource(data.value());
                }else if(classz[0].isAnnotationPresent(DataSource.class)){
                    DataSource data = classz[0].getAnnotation(DataSource.class);
                    DynamicDataSourceHolder.setDataSource(data.value());
                }
            }
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
        }
    }
}
