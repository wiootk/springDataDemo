package com.jun.util;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
// @Target(value={TYPE,FIELD,METHOD,PARAMETER,CONSTRUCTOR,LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)//生命周期
@Documented
@Inherited
public @interface AccessAllow {
    String EVERYBODY = "EVERYBODY";
    String PREROGATIVE = "*";
    String[] value() default {};
}
