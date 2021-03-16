package cn.erectpine.common.aspect;

import cn.erectpine.common.annotation.LogIgnore;
import cn.erectpine.common.enums.CodeMsgEnum;
import cn.erectpine.common.enums.CommonEnum;
import cn.erectpine.common.enums.LogTypeEnum;
import cn.erectpine.common.util.AspectUtil;
import cn.erectpine.common.util.Assert;
import cn.erectpine.common.util.IpUtils;
import cn.erectpine.common.util.ServletUtil;
import cn.erectpine.common.web.pojo.ApiLog;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

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
    @Pointcut("execution(* cn.erectpine.*.project.*.controller..*.*(..))")
    public void logPointCut() {
    }
    
    /**
     * 配置切入点
     */
    @Pointcut("@annotation(cn.erectpine.common.annotation.Log)")
    public void pointCut() {
    }
    
    /**
     * 日志切面
     * 记录日志
     */
    @Around("logPointCut()")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        LogIgnore logIgnore = AspectUtil.getAnnotationLog(joinPoint, LogIgnore.class);
    
        // 开始记录日志
        ApiLog apiLog = new ApiLog()
                .setRequestId(ServletUtil.getAttribute(CommonEnum.requestId.name()).toString())
                .setHeaders(JSON.toJSONString(ServletUtil.getHeaders()))
                .setStartTime(LocalDateTime.now())
                .setStatus(CodeMsgEnum.SUCCESS.getCode());
    
        if (logIgnore == null || logIgnore.ignoreRequestData()) {
            apiLog.setRequestData(JSON.toJSONString(joinPoint.getArgs()));
        }
    
        Object proceed = null;
        // 调用方法
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            // 记录异常日志
            if (logIgnore == null || logIgnore.ignoreStacktrace()) {
                apiLog.setStacktrace(JSON.toJSONString(e, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue));
            }
            apiLog.setStatus(CodeMsgEnum.ERROR.getCode())
                  .setErrorMessage(e.getMessage());
            throw e;
        } finally {
            if (logIgnore == null || logIgnore.ignoreResponseData()) {
                apiLog.setResponseData((JSON.toJSONString(proceed)));
            }
        
            HttpServletRequest request = ServletUtil.getRequest();
        
            // 记录日志
            Assert.notNull(request, "请求对象不能为空");
            apiLog.setEndTime(LocalDateTime.now())
                  .setConsumeTime(Duration.between(apiLog.getStartTime(), apiLog.getEndTime()).toMillis())
                  .setIp(IpUtils.getIpAddr(request))
                  .setUrl(request.getRequestURL().toString())
                  .setAuthorization(request.getHeader("Authorization"))
                  .setMethod(AspectUtil.getMethodName(joinPoint));
            consoleLogSync(apiLog);
        }
        
        return proceed;
    }
    
    /**
     * 将日志输出到控制台
     * TODO 将日志保存到数据库
     *
     * @param apiLog {@link ApiLog}
     */
    @Async
    public void consoleLogSync(ApiLog apiLog) {
        Map<String, Object> logMap = BeanUtil.beanToMap(apiLog, false, true);
        if (CodeMsgEnum.SUCCESS.getCode().equals(apiLog.getStatus())) {
            log.info(LogTypeEnum.INFO.getDelimiter());
            logMap.forEach((s, o) -> log.info(s + ": {}", o));
            log.info(LogTypeEnum.END.getDelimiter());
        } else {
            log.warn(LogTypeEnum.WARN.getDelimiter());
            logMap.forEach((s, o) -> log.warn(s + ": {}", o));
            log.warn(LogTypeEnum.END.getDelimiter());
        }
        
    }
    
}
