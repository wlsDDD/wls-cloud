package cn.erectpine.common.web.handler;

import cn.erectpine.common.enums.ActiveEnum;
import cn.erectpine.common.enums.CodeMsgEnum;
import cn.erectpine.common.enums.SystemEnum;
import cn.erectpine.common.properties.SpringYml;
import cn.erectpine.common.util.CoreUtil;
import cn.erectpine.common.util.EmailUtil;
import cn.erectpine.common.web.ResponseTemplate;
import cn.erectpine.common.web.exception.BaseException;
import cn.erectpine.common.web.exception.BusinessException;
import cn.erectpine.common.web.pojo.ApiLog;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired private EmailUtil emailUtil;
    @Autowired private SpringYml springYml;
    
    
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
    
        // 处理未知异常
        log.error("【全局异常拦截】{}", "未定义异常类型", e);
        ApiLog apiLog = (ApiLog) request.getAttribute(SystemEnum.apiLog.name());
        // 发送邮件
        String subject = StrUtil.format("{}服务-{}环境-发现异常，请排查！", springYml.getApplicationName(), springYml.getActive());
        String text = JSON.toJSONString(apiLog);
        emailUtil.sendSimpleMail(subject, CoreUtil.jsonDelEscape(text));
        // 定义返回-正式环境屏蔽错误信息
        if (ActiveEnum.prod.name().equals(springYml.getActive())) {
            return ResponseTemplate.error(CodeMsgEnum.UNKNOWN_PROD_ERROR);
        }
        return ResponseTemplate.error(CodeMsgEnum.UNKNOWN_DEV_ERROR);
    
    }
    
}
