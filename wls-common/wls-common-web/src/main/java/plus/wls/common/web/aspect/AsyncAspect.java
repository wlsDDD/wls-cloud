package plus.wls.common.web.aspect;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import plus.wls.common.core.constant.GlobalConstants;
import plus.wls.common.core.util.Pines;
import plus.wls.common.web.pojo.AsyncLog;
import plus.wls.common.web.util.AspectUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 异步方法执行切面
 * 收集异步任务日志
 *
 * @author wls
 * @date 2022-06-07 19:08:35
 * @since 1.0.0
 */
@Slf4j
@Aspect
@Component
public class AsyncAspect {
    
    @Around("@annotation(async)")
    public Object around(final ProceedingJoinPoint joinPoint, Async async) throws Throwable {
        AsyncLog asyncLog = new AsyncLog();
        LocalDateTime startTime = LocalDateTime.now();
        asyncLog.setStartTime(startTime).setMethodName(AspectUtil.getMethodName(joinPoint)).setParam(new JSONArray(joinPoint.getArgs()));
        try {
            Object proceed = joinPoint.proceed();
            asyncLog.setStatus(0);
            return proceed;
        } catch (Exception e) {
            asyncLog.setStatus(1)
                    .setErrorMsg(e.getMessage())
                    .setSimpleStacktrace(new JSONArray(Pines.toSimpleStackTrace(e, GlobalConstants.basePackage)))
                    .setStacktrace(new JSONArray(e.getStackTrace()));
            log.error("异步任务执行异常", e);
            throw e;
        } finally {
            LocalDateTime endTime = LocalDateTime.now();
            asyncLog.setEndTime(endTime).setConsumeTime(LocalDateTimeUtil.between(startTime, endTime, ChronoUnit.MILLIS));
            // TODO 保存日志
        }
    }
    
}
