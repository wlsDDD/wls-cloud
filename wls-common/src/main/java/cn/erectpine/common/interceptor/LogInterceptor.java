package cn.erectpine.common.interceptor;

import cn.erectpine.common.enums.SystemAttributeEnum;
import cn.erectpine.common.web.pojo.ApiLog;
import cn.hutool.core.util.IdUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成请求ID
 * 拦截器
 * 封装返回结果
 *
 * @Author wls
 * @Date 2021/3/16 10:31
 */
public class LogInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uuid = IdUtil.simpleUUID();
        request.setAttribute(SystemAttributeEnum.requestId.name(), uuid);
        request.setAttribute(SystemAttributeEnum.apiLog.name(), new ApiLog().setRequestId(uuid));
        return true;
    }
    
}
