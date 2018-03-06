package com.jun.util;

import com.jun.entity.listener.User3EntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.core.annotation.AliasFor;
import springfox.documentation.service.ApiListing;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented//注解包含在Javadoc中
@Inherited//注解可以被子类继承
@EntityListeners({User3EntityListener.class})
//@Entity
@Table
public @interface MyEntity {
//    @AliasFor(annotation = Entity.class, attribute = "name")
//    @AliasFor(annotation = Entity.class)
//    String name() default"";
//    @AliasFor(annotation = EntityListeners.class)
    Class[] listener() default {};



}






