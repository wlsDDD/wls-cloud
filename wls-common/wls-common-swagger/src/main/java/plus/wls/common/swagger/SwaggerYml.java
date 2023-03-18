package plus.wls.common.swagger;

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
@ConfigurationProperties("wls.swagger")
public class SwaggerYml {
    
    /**
     * 加密开关 默认false
     */
    private Boolean enabled = false;
    /**
     * 组名称
     */
    private String groupName;
    /**
     * 基本包
     */
    private String basePackage;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 组名称
     */
    private String version;
    
    /**
     * 联系人姓名
     */
    private String contactName;
    /**
     * 联系人url
     */
    private String contactUrl;
    /**
     * 联系电子邮件
     */
    private String contactEmail;
    
}
