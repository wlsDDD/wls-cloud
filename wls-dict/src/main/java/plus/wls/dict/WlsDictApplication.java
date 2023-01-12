package plus.wls.dict;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import static plus.wls.common.core.constant.GlobalConstants.*;

/**
 * wls-数据字典服务
 *
 * @author wls
 * @since 2021-03-18 16:29:10
 */
@EnableFeignClients(basePackages = BASE_PACKAGE)
@EnableDiscoveryClient
@Slf4j
@SpringBootApplication(scanBasePackages = BASE_PACKAGE)
public class WlsDictApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WlsDictApplication.class, args);
        log.info("微服务: {}-{} 启动完成", serviceName, active);
    }
    
}
