package cn.erectpine.common.web.handler;

import cn.erectpine.common.enums.CodeMsgEnum;
import cn.erectpine.common.enums.LogTypeEnum;
import cn.erectpine.common.util.FixUtil;
import cn.erectpine.common.util.MailServer;
import cn.erectpine.common.web.HttpResult;
import cn.erectpine.common.web.exception.BusinessException;
import cn.erectpine.common.web.pojo.ApiLog;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 全局异常处理程序
 *
 * @author wls
 * @date 2021/01/13
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {
    
    @Autowired private MailServer mailServer;
    
    @ExceptionHandler(Throwable.class)
    public HttpResult caughtException(HttpServletRequest request, HttpServletResponse response, Throwable e) {
        if ((e instanceof HttpMessageConversionException)) {
            log.warn("【全局异常拦截】{}", "参数不合法, 请检查参数后重试");
            return HttpResult.error(CodeMsgEnum.ARG_VERIFY_ERROR.setMsg(e.getMessage()));
        }
        if ((e instanceof MethodArgumentNotValidException)) {
            log.warn("【全局异常拦截】{}", "参数不合法, 请检查参数后重试");
            return HttpResult.error(CodeMsgEnum.ARG_VERIFY_ERROR.setMsg(e.getMessage()));
        }
    
        if ((e instanceof IllegalArgumentException)) {
            log.warn("【全局异常拦截】{}", "参数不合法");
            return HttpResult.error(CodeMsgEnum.ARG_VERIFY_ERROR);
        }
    
        if ((e instanceof BusinessException)) {
            log.warn("【全局异常拦截】{}", "业务类异常");
            return HttpResult.error(CodeMsgEnum.BUSINESS_ERROR.setMsg(e.getMessage()));
        }
        
        // 处理未知异常-生产环境屏蔽错误信息
        log.error("【全局异常拦截】{}", "未定义异常类型", e);
        return HttpResult.error(CodeMsgEnum.UNKNOWN_ERROR);
    }
    
    /**
     * 重写返回结果
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ApiLog apiLog = FixUtil.getApiLog();
        HttpResult httpResult = null == body ?
                HttpResult.success() : body instanceof HttpResult ?
                (HttpResult) body : HttpResult.success(body);
        apiLog.setResponseData(JSONUtil.parse(httpResult));
        FixUtil.setApiLog(apiLog);
        consoleLog(apiLog);
        // 未知异常时发送邮件
        if (CodeMsgEnum.UNKNOWN_ERROR.equals(apiLog.getStatus())) {
            mailServer.sendApiLog(apiLog);
        }
        return httpResult;
    }
    
    /**
     * 对返回类型支持
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }
    
    /**
     * 将日志输出到控制台
     * TODO 将日志保存到数据库
     *
     * @param apiLog {@link ApiLog}
     */
    public static void consoleLog(ApiLog apiLog) {
        Map<String, Object> logMap = BeanUtil.beanToMap(apiLog, false, false);
        if (CodeMsgEnum.SUCCESS.equals(apiLog.getStatus())) {
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
