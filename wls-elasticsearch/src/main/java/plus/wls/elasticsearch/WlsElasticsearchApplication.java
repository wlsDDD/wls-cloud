package plus.wls.elasticsearch;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import plus.wls.common.core.constant.GlobalConstants;

/**
 * wls-es搜索服务
 *
 * @author wls
 * @since 2021/10/18 15:38:53
 */
@EnableDiscoveryClient
@Slf4j
@SpringBootApplication(scanBasePackages = "plus.wls")
public class WlsElasticsearchApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WlsElasticsearchApplication.class, args);
        log.info(StrUtil.format("微服务: {}-{} 启动完成", GlobalConstants.serviceName, GlobalConstants.active));
    }
    
}
