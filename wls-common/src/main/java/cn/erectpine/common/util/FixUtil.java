package cn.erectpine.common.util;

import cn.erectpine.common.enums.SystemAttributeEnum;
import cn.erectpine.common.web.pojo.ApiLog;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目一些不便移植的工具类
 *
 * @Author wls
 * @Date 2021/4/4 18:15
 */
public class FixUtil {
    
    /**
     * 请求唯一ID
     *
     * @return {@link ApiLog}
     */
    public static String getRequestId() {
        HttpServletRequest request = ServletUtil.getRequest();
        return request.getAttribute(SystemAttributeEnum.requestId.name()).toString();
    }
    
    /**
     * 获得api日志
     *
     * @return {@link ApiLog}
     */
    public static ApiLog getApiLog() {
        HttpServletRequest request = ServletUtil.getRequest();
        return (ApiLog) request.getAttribute(SystemAttributeEnum.apiLog.name());
    }
    
    /**
     * 设置api日志
     *
     * @param apiLog api日志
     */
    public static void setApiLog(ApiLog apiLog) {
        HttpServletRequest request = ServletUtil.getRequest();
        request.setAttribute(SystemAttributeEnum.apiLog.name(), apiLog);
    }
    
}
