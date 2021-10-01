package cn.erectpine.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * wls系统应用
 *
 * @author wls
 * @since 2021/01/20 14:28:02
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = "cn.erectpine")
public class WlsGatewayApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WlsGatewayApplication.class, args);
        System.err.println("(♥◠‿◠)ﾉﾞ  wls-网关服务-启动完成   ლ(´ڡ`ლ)ﾞ ");
    }
    
}
