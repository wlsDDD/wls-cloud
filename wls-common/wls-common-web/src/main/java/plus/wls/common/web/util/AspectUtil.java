package plus.wls.common.web.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;

/**
 * 切面工具类
 *
 * @author wls
 * @since 2021/1/15 9:48
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
     * 获取指定注解
     */
    public static <T extends Annotation> T getAnnotation(JoinPoint joinPoint, Class<T> clazz) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod().getAnnotation(clazz);
    }
    
}
