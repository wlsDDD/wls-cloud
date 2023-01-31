package plus.wls.common.web.config;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import plus.wls.common.web.interceptor.ContextInterceptor;

/**
 * 项目拦截器配置
 *
 * @author wls
 * @since 2021/3/16 10:37
 */
@Configuration
@Import(SpringUtil.class)
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
