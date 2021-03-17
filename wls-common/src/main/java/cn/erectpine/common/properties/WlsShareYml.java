package cn.erectpine.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 共享yml配置
 *
 * @Author wls
 * @Date 2021/3/15 17:31
 */
@Data
@Component
@ConfigurationProperties("share")
public class WlsShareYml {
    
    /**
     * 邮件发送人
     */
    private String emailTo;
    
    /**
     * 邮件收件人
     */
    private String[] addressee;
    
    /**
     * 堆栈日志过滤关键字
     */
    private String stacktrace;
    
    /**
     * 接口日志切面
     */
    public static String apiLogPointCut;
    
}
