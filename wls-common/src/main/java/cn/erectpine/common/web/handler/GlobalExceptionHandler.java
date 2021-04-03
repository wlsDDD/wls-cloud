package cn.erectpine.common.web.handler;

import cn.erectpine.common.constant.GlobalConstants;
import cn.erectpine.common.enums.ActiveEnum;
import cn.erectpine.common.enums.CodeMsgEnum;
import cn.erectpine.common.enums.SystemEnum;
import cn.erectpine.common.properties.WlsShareYml;
import cn.erectpine.common.util.CoreUtil;
import cn.erectpine.common.util.MailServer;
import cn.erectpine.common.web.ResponseTemplate;
import cn.erectpine.common.web.exception.BaseRunTimeException;
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
    
    @Autowired private MailServer mailServer;
    @Autowired private WlsShareYml wlsShareYml;
    
    
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
            return ResponseTemplate.error((BaseRunTimeException) e);
        }
    
        if ((e instanceof BaseRunTimeException)) {
            log.warn("【全局异常拦截】{}", "基础异常", e);
            return ResponseTemplate.error((BaseRunTimeException) e);
        }
    
        // 处理未知异常
        log.error("【全局异常拦截】{}", "未定义异常类型", e);
        ApiLog apiLog = (ApiLog) request.getAttribute(SystemEnum.apiLog.name());
        // 发送邮件
        String title = StrUtil.format("{}服务-{}环境-发现异常，请排查！", GlobalConstants.serviceName, GlobalConstants.active.name());
        mailServer.sendSimpleMail(title, CoreUtil.jsonDelEscape(JSON.toJSONString(apiLog)), wlsShareYml.getAddressee());
        // 定义返回-正式环境屏蔽错误信息
        if (ActiveEnum.prod.equals(GlobalConstants.active)) {
            return ResponseTemplate.error(CodeMsgEnum.UNKNOWN_PROD_ERROR);
        }
        return ResponseTemplate.error(CodeMsgEnum.UNKNOWN_DEV_ERROR);
    }
    
}
