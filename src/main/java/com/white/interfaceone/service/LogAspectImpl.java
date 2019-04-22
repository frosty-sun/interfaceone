package com.white.interfaceone.service;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @version v1.0
 * @ProjectName: informationEntry
 * @ClassName: LogAspect
 * @Description: TODO(注解类实现)
 * @Author: sunbo@pgytesting.cn
 * @Date: 2019/4/9 21:38
 */
@Aspect
@Order(100)
@Component
public class LogAspectImpl {
    public static final Logger log = LoggerFactory.getLogger(LogAspectImpl.class);
    public static final String DATE_FORMAT = "yyyy:MM:dd HH:mm:ss";


    /**
     * execution the scan of pakage 切点package
     */
    @Pointcut("execution( * com.white.interfaceone.controller..*(..))")
    public void serviceLog() {

    }

    @Around("serviceLog()")
    public Object around(ProceedingJoinPoint joinPoint) { // ProceedingJoinPoint 为JoinPoint 的子类，在父类基础上多了proceed()方法，用于增强切面
        try {
            // 获取方法签名
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //java reflect相关类，通过反射得到注解
            Method method = signature.getMethod();
            Class<?> targetClass = method.getDeclaringClass();
            // 获取请求地址
            String url;
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request;
            if (sra != null) {
                request = sra.getRequest();
                url = "接口：\"" + request.getRequestURI() + "\"";
            } else {
                url = "接口：\"/" + method.getName() + "\"";
            }


            StringBuilder classAndMethod = new StringBuilder();

            //获取类注解Log
            LogAspect classAnnotation = targetClass.getAnnotation(LogAspect.class);
            //获取方法注解Log
            LogAspect methodAnnotation = method.getAnnotation(LogAspect.class);

            //如果类上Log注解不为空，则执行proceed()
            if (classAnnotation != null) {
                if (classAnnotation.ignore()) {
                    //proceed() 方法执行切面方法，并返回方法返回值
                    return joinPoint.proceed();
                }
                classAndMethod.append(classAnnotation.value()).append("-");
            }

            //如果方法上Log注解不为空，则执行proceed()
            if (methodAnnotation != null) {
                if (methodAnnotation.ignore()) {
                    return joinPoint.proceed();
                }
                classAndMethod.append(methodAnnotation.value());
            }

            //拼凑目标类名和参数名
            // String target = targetClass.getName().substring(targetClass.getName().lastIndexOf(".")+1) + "/\"" + method.getName() + "\"";
            //String target = "接口：/\"" + method.getName() + "\"";
            String params = JSONObject.toJSONStringWithDateFormat(joinPoint.getArgs(), DATE_FORMAT, SerializerFeature.WriteMapNullValue);

            log.info("\n{} 开始调用--> {} 参数:{}", classAndMethod.toString(), url, params);

            long start = System.currentTimeMillis();
            //如果类名上和方法上都没有Log注解，则直接执行 proceed()
            Object result = joinPoint.proceed();
            long timeConsuming = System.currentTimeMillis() - start;

            //log.info("\n调用结束--> {} 返回值:{} 耗时:{}ms" , target, JSONObject.toJSONStringWithDateFormat(result, dateformat, SerializerFeature.WriteMapNullValue), timeConsuming);
            log.info("\n{} 调用结束<-- {} 返回值:{} 耗时:{}ms", classAndMethod.toString(), url, JSONObject.toJSONStringWithDateFormat(result, DATE_FORMAT, SerializerFeature.WriteMapNullValue), timeConsuming);
            return result;
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
        }
        return null;
    }
}
