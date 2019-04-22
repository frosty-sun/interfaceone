package com.white.interfaceone.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version v1.0
 * @ProjectName: informationEntry
 * @ClassName: Log
 * @Description: TODO(一句话描述该类的功能)
 * @Author: sunbo@pgytesting.cn
 * @Date: 2019/4/9 21:50
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAspect {
    String value() default "";

    boolean ignore() default false;
}
