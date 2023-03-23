package plus.wls.common.log.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import plus.wls.common.log.annotation.Log;

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
     * 日志切面-基于注解
     * 记录日志
     */
    @Around("@annotation(log)")
    public Object around(final ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        return LogAspect.logAround(joinPoint);
    }
    
}
