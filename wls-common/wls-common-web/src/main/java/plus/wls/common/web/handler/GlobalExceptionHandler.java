package plus.wls.common.web.handler;

import cn.hutool.core.bean.BeanUtil;
import plus.wls.common.core.enums.CodeInfoEnum;
import plus.wls.common.core.enums.LogTypeEnum;
import plus.wls.common.core.exception.BusinessException;
import plus.wls.common.core.pojo.ApiLog;
import plus.wls.common.mail.MailServer;
import plus.wls.common.web.context.HttpContext;
import plus.wls.common.web.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 全局异常处理程序
 *
 * @author wls
 * @since 2021/01/13
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final String MSG_PREFIX = "【全局异常拦截】-[{}]";
    @Autowired
    private MailServer mailServer;
    
    /**
     * 将日志输出到控制台
     * TODO 将日志保存到数据库
     *
     * @author wls
     * @date 2022-04-24 10:17:04
     * @since 1.0.0
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
    @ExceptionHandler(Throwable.class)
    public Result<?> caughtException(HttpServletRequest request, HttpServletResponse response, Throwable e) {
        if ((e instanceof BindException)) {
            log.warn(MSG_PREFIX, "请求参数不合法 参数校验未通过", e);
            return Result.fail(CodeInfoEnum.ARG_VERIFY_ERROR).paramErrors(((BindException) e)
                    .getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, fieldError -> Optional.ofNullable(fieldError.getDefaultMessage()).orElse(""))));
        }
        if ((e instanceof HttpMessageConversionException || e instanceof IllegalArgumentException)) {
            log.warn(MSG_PREFIX, "请求参数不合法 body必传", e);
            return Result.fail(CodeInfoEnum.ARG_VERIFY_ERROR.setInfo(e.getMessage()));
        }
        if ((e instanceof BusinessException)) {
            log.warn(MSG_PREFIX, "业务类异常", e);
            return Result.fail(CodeInfoEnum.BUSINESS_ERROR.setInfo(e.getMessage()));
        }
        // 处理未知异常-生产环境屏蔽错误信息
        log.warn(MSG_PREFIX, "未知异常", e);
        return Result.fail(CodeInfoEnum.UNKNOWN_ERROR);
    }
    
}
