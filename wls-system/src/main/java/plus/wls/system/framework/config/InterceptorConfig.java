package plus.wls.system.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import plus.wls.system.framework.interceptor.SaveLogInterceptor;

/**
 * 添加拦截器
 *
 * @author wls
 * @since 2021/3/16 10:37
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    
    /**
     * 注册拦截器
     *
     * @param registry 注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaveLogInterceptor())
                .addPathPatterns("/**");
    }
    
}
