package cn.toy.www.apiversion;

import org.apache.ibatis.annotations.Mapper;

import java.lang.annotation.*;

/**
 * 接口版本号注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapper
public @interface ApiVersion {

    /**
     * 版本号，从1开始递增
     * @return
     */
    int value() default 1;
}
