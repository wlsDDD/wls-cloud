package plus.wls.common.web.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import plus.wls.common.core.enums.ActiveEnum;

/**
 * 共享yml配置
 *
 * @author wls
 * @since 2021/3/15 17:31
 */
@Data
@Component
@ConfigurationProperties("share")
public class ShareYml {
    
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 服务名
     */
    private String serviceName;
    /**
     * 项目启动环境
     */
    private ActiveEnum active;
    /**
     * 版本
     */
    private String version;
    /**
     * 版权年份
     */
    private String copyrightYear;
    /**
     * 文件路径
     */
    private String profile;
    /**
     * 获取ip地址开关
     */
    private Boolean addressEnabled = false;
    /**
     * 堆栈信息过滤关键字
     */
    private String logFilter;
    
}
