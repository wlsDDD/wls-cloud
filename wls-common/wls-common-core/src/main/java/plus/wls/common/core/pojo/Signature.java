package plus.wls.common.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 验证签名
 *
 * @author wls
 * @since 2021/10/01 08:52:35
 */
@Component
@Data
@Accessors(chain = true)
@ConfigurationProperties("wls.signature")
public class Signature {
    
    /**
     * 签名开关
     * 默认false 不验证签名
     */
    private Boolean enable = false;
    /**
     * key
     */
    private String appKey;
    /**
     * secret
     */
    private String appSecret;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 版本
     */
    private String version;
    /**
     * 随机字符串（建议UUID）
     */
    private String randomStr;
    /**
     * 签名
     */
    private String sign;
    
}
