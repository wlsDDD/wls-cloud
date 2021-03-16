package cn.erectpine.common.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 项目相关配置
 *
 * @Author wls
 * @Date 2021/3/16 9:25
 */
@Data
@Component
public class SpringYml {
    
    /**
     * 项目启动环境
     */
    @Value("${spring.profiles.active}")
    private String active;
    
    /**
     * 应用程序名称
     */
    @Value("${spring.application.name}")
    private String applicationName;
    
}
