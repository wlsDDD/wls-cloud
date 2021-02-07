package cn.erectpine.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * wls系统应用
 *
 * @author wls
 * @date 2021/01/20 14:28:02
 */
@EnableDiscoveryClient
@SpringBootApplication
public class WlsSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(WlsSystemApplication.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ  ");
	}

}
