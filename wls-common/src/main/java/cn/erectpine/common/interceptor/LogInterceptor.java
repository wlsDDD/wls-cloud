package cn.erectpine.common.interceptor;

import cn.erectpine.common.enums.CommonEnum;
import cn.hutool.core.util.IdUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成请求ID拦截器
 *
 * @Author wls
 * @Date 2021/3/16 10:31
 */
public class LogInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(CommonEnum.requestId.name(), IdUtil.simpleUUID());
        return true;
    }
}