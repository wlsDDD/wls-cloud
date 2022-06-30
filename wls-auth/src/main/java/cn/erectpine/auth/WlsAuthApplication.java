package cn.erectpine.auth;

import cn.erectpine.common.core.constant.GlobalConstants;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * wls系统应用
 *
 * @author wls
 * @since 2021/01/20 14:28:02
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.erectpine")
@Slf4j
public class WlsAuthApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WlsAuthApplication.class, args);
        log.info(StrUtil.format("微服务: {}-{} 启动完成", GlobalConstants.serviceName, GlobalConstants.active));
    }
    
}