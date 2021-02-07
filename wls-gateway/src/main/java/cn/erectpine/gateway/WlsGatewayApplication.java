package cn.erectpine.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * wls系统应用
 *
 * @author wls
 * @date 2021/01/20 14:28:02
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class WlsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WlsGatewayApplication.class, args);
		System.err.println("(♥◠‿◠)ﾉﾞ  网关模块启动成功   ლ(´ڡ`ლ)ﾞ  ");
	}

}
