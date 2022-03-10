package cn.erectpine.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * wls系统应用
 *
 * @author wls
 * @since 2021/01/20 14:28:02
 */
@Slf4j
@SpringBootApplication
public class WlsSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(WlsSystemApplication.class, args);
		log.info("   (♥◠‿◠)ﾉﾞ  wls系统服务-启动完成   ლ(´ڡ`ლ)ﾞ  ");
	}

}
