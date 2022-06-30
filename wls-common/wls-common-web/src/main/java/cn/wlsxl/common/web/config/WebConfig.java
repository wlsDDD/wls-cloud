package cn.wlsxl.common.web.config;

import cn.wlsxl.common.web.interceptor.ContextInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 项目拦截器配置
 *
 * @author wls
 * @since 2021/3/16 10:37
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    /**
     * 注册拦截器
     *
     * @param registry 注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ContextInterceptor()).addPathPatterns("/**");
    }
    
}
