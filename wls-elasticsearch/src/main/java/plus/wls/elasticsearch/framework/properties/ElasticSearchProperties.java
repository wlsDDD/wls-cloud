package plus.wls.elasticsearch.framework.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * es搜索配置类
 *
 * @author wls
 * @since 2021/10/18 15:42:23
 */

@Component
@ConfigurationProperties("wls.es")
@Data
@Accessors(chain = true)
public class ElasticSearchProperties {
    
    /**
     * 主机
     */
    private String host;
    /**
     * 端口
     */
    private int port;
    /**
     * 连接方式 http/https
     */
    private String scheme;
    /**
     * 超时
     */
    private Integer timeOut;
    /**
     * 索引
     */
    private String index;
    
}
