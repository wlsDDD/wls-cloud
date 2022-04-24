package cn.erectpine.common.web.properties;

import cn.erectpine.common.core.enums.ActiveEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 共享yml配置
 *
 * @author wls
 * @since 2021/3/15 17:31
 */
@Data
@Component
@ConfigurationProperties("share")
public class WlsShareYml {
    /**
     * 服务名
     */
    private String serviceName;
    /**
     * 项目启动环境
     */
    private ActiveEnum active;
    
    /**
     * 邮件发送人
     */
    private String emailFrom;
    /**
     * 邮件收件人
     */
    private String[] addressee;
    
    /**
     * 堆栈信息过滤关键字
     */
    private String stackFilter;
    
}
