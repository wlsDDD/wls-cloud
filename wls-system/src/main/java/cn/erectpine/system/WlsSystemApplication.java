package cn.erectpine.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * wls系统应用
 *
 * @author wls
 * @since 2021/01/20 14:28:02
 */
@Slf4j
@ComponentScan(basePackages = {"cn.erectpine", "cn.hutool.extra.spring"})
@EnableAsync
@EnableFeignClients(basePackages = "cn.erectpine")
@EnableDiscoveryClient
@SpringBootApplication
public class WlsSystemApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WlsSystemApplication.class, args);
        log.info("   (♥◠‿◠)ﾉﾞ  wls系统服务-启动完成   ლ(´ڡ`ლ)ﾞ  ");
    }
    
}
