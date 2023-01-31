package plus.wls.common.web.interceptor;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import plus.wls.common.core.pojo.ApiLog;
import plus.wls.common.web.context.Context;
import plus.wls.common.web.context.HttpContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 上下文拦截器
 * 生成请求ID
 * 封装返回结果
 *
 * @author wls
 * @since 2021/3/16 10:31
 */
@Order(-100)
public class ContextInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpContext.setContext(new Context().setRequest(request).setResponse(response)
                                            .setApiLog(new ApiLog()));
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HttpContext.removeContext();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
    
}
