package cn.erectpine.system;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * wls系统应用
 *
 * @author wls
 * @date 2021/01/20 14:28:02
 */
//@Slf4j
@SpringCloudApplication
public class WlsSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(WlsSystemApplication.class, args);
//		log.warn("(♥◠‿◠)ﾉﾞ  {}   ლ(´ڡ`ლ)ﾞ  ","系统模块启动成功");
	}

}
