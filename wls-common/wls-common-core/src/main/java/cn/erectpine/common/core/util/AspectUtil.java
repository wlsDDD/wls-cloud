package cn.erectpine.common.core.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 切面工具类
 *
 * @Author wls
 * @Date 2021/1/15 9:48
 */
@Slf4j
public class AspectUtil {
    
    /**
     * 获取方法全名
     */
    public static String getMethodName(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        return className + "." + methodName + "()";
    }
    
    /**
     * 是否存在注解，如果存在就获取
     */
    public static <T extends Annotation> T getAnnotation(JoinPoint joinPoint, Class<T> clazz) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(clazz);
        }
        return null;
    }
    
}
