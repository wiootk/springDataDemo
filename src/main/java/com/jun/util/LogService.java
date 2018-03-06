package com.jun.util;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogService {
    //描述
    String desc() default "";
    String oprateType() default "";
    String objId() default "";
    String objType() default "";
}
