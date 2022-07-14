package cn.erectpine.gencode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;


/**
 * wls代码生成服务
 *
 * @author wls
 * @since 2021/01/20 14:28:02
 */
@Slf4j
@SpringBootApplication
public class WlsGencodeApplication {
    
    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(WlsGencodeApplication.class, args);
        ConfigurableEnvironment env = app.getEnvironment();
        log.info("\n\t--------------微服务: {}-{} 启动完成----------------", env.getProperty("spring.application.name"), env.getProperty("spring.profiles.active"));
    }
    
}
