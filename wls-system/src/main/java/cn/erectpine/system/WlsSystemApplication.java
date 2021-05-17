package cn.erectpine.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * wls系统应用
 *
 * @author wls
 * @date 2021/01/20 14:28:02
 */
@EnableAsync
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.erectpine")
public class WlsSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(WlsSystemApplication.class, args);
		System.err.println("(♥◠‿◠)ﾉﾞ  wls系统服务-启动完成   ლ(´ڡ`ლ)ﾞ  ");
	}

}
