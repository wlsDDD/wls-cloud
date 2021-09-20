package cn.erectpine.system.framework.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 应用程序配置
 *
 * @author wls
 * @since 2021/4/4 20:05
 */
@ComponentScan(basePackages = {"cn.hutool.extra.spring"})
@EnableAsync
@EnableFeignClients(basePackages = "cn.erectpine")
@EnableDiscoveryClient
@Configuration
public class ApplicationConfig {

}
