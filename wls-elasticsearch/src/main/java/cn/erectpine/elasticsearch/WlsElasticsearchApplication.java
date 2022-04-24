package cn.erectpine.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * wls-es搜索服务
 *
 * @author wls
 * @since 2021/10/18 15:38:53
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.erectpine")
public class WlsElasticsearchApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WlsElasticsearchApplication.class, args);
        System.err.println("(♥◠‿◠)ﾉﾞ  wls-es搜索-服务-启动完成   ლ(´ڡ`ლ)ﾞ  ");
    }
    
}
