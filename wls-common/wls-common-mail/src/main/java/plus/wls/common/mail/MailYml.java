package plus.wls.common.mail;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 邮件配置信息
 *
 * @author wls
 * @date 2022-07-14 14:08:50
 * @since 1.0.0
 */
@Data
@Component
@ConfigurationProperties("wls.share.mail")
public class MailYml {
    
    /**
     * 邮件发送人
     */
    private String emailFrom;
    /**
     * 邮件收件人
     */
    private String[] addressee;
    
}
