package cn.erectpine.dict;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * wls-数据字典服务
 *
 * @author wls
 * @date 2021-03-18 16:29:10
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.erectpine")
public class WlsDictApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WlsDictApplication.class, args);
		System.err.println("(♥◠‿◠)ﾉﾞ  wls数据字典服务-启动完成   ლ(´ڡ`ლ)ﾞ  ");
	}
	
}
