package plus.wls.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import static plus.wls.common.core.constant.GlobalConstants.*;

/**
 * wls系统应用
 *
 * @author wls
 * @since 2021/01/20 14:28:02
 */
@Slf4j
@ComponentScan(basePackages = {BASE_PACKAGE_NAME})
@EnableAsync
@EnableFeignClients(basePackages = BASE_PACKAGE_NAME)
@EnableDiscoveryClient
@SpringBootApplication
public class WlsSystemApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WlsSystemApplication.class, args);
        log.info("\n\t---{}微服务: {}-{} 启动完成\n\t---", "\n\t", serviceName, active);
    }
    
}
