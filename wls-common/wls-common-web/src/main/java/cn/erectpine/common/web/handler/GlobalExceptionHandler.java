package cn.erectpine.common.web.handler;

import cn.erectpine.common.core.enums.CodeInfoEnum;
import cn.erectpine.common.core.enums.LogTypeEnum;
import cn.erectpine.common.core.exception.BusinessException;
import cn.erectpine.common.core.jdkboost.map.PineStrMap;
import cn.erectpine.common.core.pojo.ApiLog;
import cn.erectpine.common.web.context.HttpContext;
import cn.erectpine.common.web.mail.MailServer;
import cn.erectpine.common.web.pojo.Result;
import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理程序
 *
 * @author wls
 * @since 2021/01/13
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @Autowired private MailServer mailServer;
    
    @ExceptionHandler(Throwable.class)
    public Result<?> caughtException(HttpServletRequest request, HttpServletResponse response, Throwable e) {
        if ((e instanceof BindException)) {
            log.warn("【全局异常拦截】-[参数不合法]", e);
            return Result.fail(CodeInfoEnum.ARG_VERIFY_ERROR).setParamErrors(getValidatedError((BindException) e));
        }
        if ((e instanceof HttpMessageConversionException)) {
            log.warn("【全局异常拦截】-[参数不合法]", e);
            return Result.fail(CodeInfoEnum.ARG_VERIFY_ERROR.setInfo(e.getMessage()));
        }
        
        if ((e instanceof IllegalArgumentException)) {
            log.warn("【全局异常拦截】-[参数不合法]", e);
            return Result.fail(CodeInfoEnum.ARG_VERIFY_ERROR.setInfo(e.getMessage()));
        }
    
        if ((e instanceof BusinessException)) {
            log.warn("【全局异常拦截】-[业务类异常]", e);
            return Result.fail(CodeInfoEnum.BUSINESS_ERROR.setInfo(e.getMessage()));
        }
    
        // 处理未知异常-生产环境屏蔽错误信息
        log.error("【全局异常拦截】-[未知异常]", e);
        return Result.fail(CodeInfoEnum.UNKNOWN_ERROR);
    }
    
    /**
     * 解析BindException获取消息提示
     *
     * @param e e
     * @return {@link List}<{@link PineStrMap}<{@link String}>>
     */
    public static PineStrMap<String> getValidatedError(BindException e) {
        PineStrMap<String> map = new PineStrMap<>();
        e.getFieldErrors().forEach(fieldError -> {
            map.putItem(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return map;
    }
    
    /**
     * 重写返回结果
     */
///    @Override
///    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//        // swagger不做统一拦截处理
//        if (StrUtil.contains(request.getURI().getPath(), "swagger")) {
//            return body;
//        }
//        ApiLog apiLog = HttpContext.getApiLog();
//        Result<?> result = null == body ?
//                Result.ok() : body instanceof Result ?
//                (Result<?>) body : Result.ok(body);
//        apiLog.setResponseData(JSONUtil.parse(result));
//        // 异常时发送邮件
/////        if (!CodeMsgEnum.SUCCESS.equals(apiLog.getStatus())) {
////            mailServer.sendApiLog();
////            consoleLog();
////        }
//        return result;
//    }
//
//    /**
//     * 对返回类型支持
//     */
//    @Override
//    public boolean supports(MethodParameter returnType, Class converterType) {
//        return true;
//    }
    
    /**
     * 将日志输出到控制台
     * TODO 将日志保存到数据库
     *
     * @param apiLog {@link ApiLog}
     */
    public static void consoleLog() {
        ApiLog apiLog = HttpContext.getApiLog();
        Map<String, Object> logMap = BeanUtil.beanToMap(apiLog, false, false);
        if (CodeInfoEnum.SUCCESS.equals(apiLog.getStatus())) {
            log.info(LogTypeEnum.SUCCESS.getDelimiter());
            logMap.forEach((s, o) -> log.info(s + ": {}", o));
            log.info(LogTypeEnum.END.getDelimiter());
        } else {
            log.warn(LogTypeEnum.FAIL.getDelimiter());
            logMap.forEach((s, o) -> log.warn(s + ": {}", o));
            log.warn(LogTypeEnum.END.getDelimiter());
        }
    }
    
}
