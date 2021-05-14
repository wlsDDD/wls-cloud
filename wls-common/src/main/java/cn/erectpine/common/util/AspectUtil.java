package cn.erectpine.common.util;

import cn.erectpine.common.annotation.LogIgnore;
import cn.erectpine.common.constant.GlobalConstants;
import cn.erectpine.common.enums.CodeMsgEnum;
import cn.erectpine.common.enums.SystemAttributeEnum;
import cn.erectpine.common.web.pojo.ApiLog;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;

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
    public static <T extends Annotation> T getAnnotationLog(JoinPoint joinPoint, Class<T> clazz) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        
        if (method != null) {
            return method.getAnnotation(clazz);
        }
        return null;
    }
    
    public static Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LogIgnore logIgnore = null;
        HttpServletRequest request = null;
        ApiLog apiLog = null;
        try {
            // 获取日志忽略注解
            logIgnore = AspectUtil.getAnnotationLog(joinPoint, LogIgnore.class);
            request = ServletUtil.getRequest();
            // 开始记录日志
            apiLog = FixUtil.getApiLog();
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
//            if (logIgnore == null || logIgnore.ignoreStacktrace()) {}
            try {
                org.springframework.util.Assert.notNull(apiLog, "日志切面异常");
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
                request.setAttribute(SystemAttributeEnum.apiLog.name(), apiLog);
            } catch (Exception e) {
                log.error("日志切面后置异常", e);
            }
        }
        return proceed;
    }
}
