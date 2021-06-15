package cn.erectpine.common.log.aspect;

import cn.erectpine.common.core.constant.GlobalConstants;
import cn.erectpine.common.core.enums.CodeMsgEnum;
import cn.erectpine.common.core.util.AspectUtil;
import cn.erectpine.common.core.util.CoreUtil;
import cn.erectpine.common.core.util.IpUtils;
import cn.erectpine.common.core.util.ServletUtil;
import cn.erectpine.common.log.annotation.LogIgnore;
import cn.erectpine.common.web.context.WlsContext;
import cn.erectpine.common.web.pojo.ApiLog;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 日志切面
 *
 * @author wls
 * @date 2021/01/15
 */
@Slf4j
@Aspect
@Component
public class LogAspect {
    /**
     * 配置切入点
     */
    @Pointcut("execution(* cn.erectpine.*.project.controller..*.*(..))")
    public void logPointCut() {
    }
    
    /**
     * 日志切面
     * 记录日志
     */
    @Around("logPointCut()")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        return logAround(joinPoint);
    }
    
    public static Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LogIgnore logIgnore = null;
        HttpServletRequest request = null;
        ApiLog apiLog = null;
        try {
            // 获取日志忽略注解
            logIgnore = AspectUtil.getAnnotation(joinPoint, LogIgnore.class);
            request = ServletUtil.getRequest();
            // 开始记录日志
            apiLog = WlsContext.getContext().getApiLog();
            apiLog.setHeaders(JSONUtil.parseObj(ServletUtil.getHeaders(request)))
                  .setStartTime(LocalDateTime.now())
                  .setStatus(CodeMsgEnum.SUCCESS);
            if (logIgnore == null || logIgnore.ignoreRequestData()) {
                apiLog.setRequestData(JSONUtil.parse(joinPoint.getArgs()));
            }
        } catch (Exception e) {
            log.error("日志切面前置异常", e);
        }
        Object proceed = null;
        // 调用方法
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            // 记录异常日志
            try {
                Assert.notNull(apiLog, "日志切面异常");
                apiLog.setStatus(CodeMsgEnum.UNKNOWN_ERROR)
                      .setErrorMsg(e.toString())
                      .setSimpleStacktrace(JSONUtil.parse(CoreUtil.getSimpleStackTrace(e, GlobalConstants.stackFilter)));
            } catch (Exception exception) {
                log.error("日志切面catch块异常", e);
            }
            throw e;
        } finally {
            try {
                Assert.notNull(apiLog, "日志切面异常");
                if (logIgnore == null || logIgnore.ignoreResponseData()) {
                    apiLog.setResponseData(JSONUtil.parse(proceed));
                }
                // 记录日志
                apiLog.setEndTime(LocalDateTime.now())
                      .setConsumeTime(Duration.between(apiLog.getStartTime(), apiLog.getEndTime()).toMillis())
                      .setIp(IpUtils.getIpAddr(request))
                      .setUrl(request.getRequestURL().toString())
                      .setRequestMethod(request.getMethod())
                      .setAuthorization(request.getHeader("Authorization"))
                      .setHandleMethod(AspectUtil.getMethodName(joinPoint));
            } catch (Exception e) {
                log.error("日志切面后置异常", e);
            }
        }
        return proceed;
    }
    
}
