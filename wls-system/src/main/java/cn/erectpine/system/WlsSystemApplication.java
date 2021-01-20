package cn.erectpine.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * wls系统应用
 *
 * @author wls
 * @date 2021/01/20 14:28:02
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "cn.erectpine")
public class WlsSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(WlsSystemApplication.class, args);
		log.warn("(♥◠‿◠)ﾉﾞ  {}   ლ(´ڡ`ლ)ﾞ  ","系统模块启动成功");
	}

}
