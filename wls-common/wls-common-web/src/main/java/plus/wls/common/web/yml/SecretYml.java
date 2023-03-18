package plus.wls.common.web.yml;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 接口加解密配置
 *
 * @author wls
 * @date 2022-06-21 14:10:28
 * @since 1.0.0
 */
@Component
@Data
@Accessors(chain = true)
@ConfigurationProperties("wls.secret")
public class SecretYml {
    
    /**
     * 加密开关 默认false
     */
    private Boolean encryptionEnable = false;
    /**
     * 解密开关 默认false
     */
    private Boolean decryptEnable = false;
    
}
