package cn.erectpine.common.log.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 日志切面-基于注解
 *
 * @author wls
 * @since 2021/4/5 11:34
 */
@Aspect
@Component
public class LogAnnotationAspect {
    
    /**
     * 配置切入点
     */
    @Pointcut("@annotation(cn.erectpine.common.log.annotation.Log)")
    public void pointCut() {
    }
    
    /**
     * 日志切面-基于注解
     * 记录日志
     */
    @Around("pointCut()")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        return LogAspect.logAround(joinPoint);
    }
    
}
