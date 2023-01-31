package plus.wls.gateway;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import plus.wls.common.core.constant.GlobalConstants;

/**
 * wls系统应用
 *
 * @author wls
 * @since 2021/01/20 14:28:02
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = "plus.wls")
@Slf4j
public class WlsGatewayApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WlsGatewayApplication.class, args);
        log.info(StrUtil.format("微服务: {}-{} 启动完成", GlobalConstants.serviceName, GlobalConstants.active));
    }
    
}
