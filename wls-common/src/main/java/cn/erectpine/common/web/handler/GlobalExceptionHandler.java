package cn.erectpine.common.web.handler;

import cn.erectpine.common.enums.CodeMsgEnum;
import cn.erectpine.common.enums.OperatingEnvironmentEnum;
import cn.erectpine.common.web.ResponseTemplate;
import cn.erectpine.common.web.exception.BaseException;
import cn.erectpine.common.web.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理程序
 *
 * @author wls
 * @date 2021/01/13
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @Value("${spring.profiles.active}")
    public static String active;
    
    
    @ExceptionHandler(Throwable.class)
    public ResponseTemplate caughtException(HttpServletRequest request, HttpServletResponse response, Throwable e) {
        
        if ((e instanceof HttpMessageConversionException)) {
            log.warn("【全局异常拦截】{}", "参数不合法, 请检查参数后重试");
            return ResponseTemplate.error(CodeMsgEnum.ARG_VERIFY_ERROR.setMsg(e.getMessage()));
        }
        
        if ((e instanceof IllegalArgumentException)) {
            log.warn("【全局异常拦截】{}", "参数不合法");
            return ResponseTemplate.error(CodeMsgEnum.ARG_VERIFY_ERROR);
        }
    
        if ((e instanceof BusinessException)) {
            log.warn("【全局异常拦截】{}", "业务类异常");
            return ResponseTemplate.error((BaseException) e);
        }
    
        if ((e instanceof BaseException)) {
            log.warn("【全局异常拦截】{}", "基础异常", e);
            return ResponseTemplate.error((BaseException) e);
        }
    
        log.error("【全局异常拦截】{}", "未定义异常类型", e);
        if (OperatingEnvironmentEnum.prod.name().equals(active)) {
            return ResponseTemplate.error(CodeMsgEnum.UNKNOWN_PROD_ERROR);
        } else {
            return ResponseTemplate.error(CodeMsgEnum.UNKNOWN_DEV_ERROR);
        }
    }
    
}
