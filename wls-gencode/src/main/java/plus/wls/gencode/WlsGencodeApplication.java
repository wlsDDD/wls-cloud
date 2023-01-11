package plus.wls.gencode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * wls代码生成服务
 *
 * @author wls
 * @since 2021/01/20 14:28:02
 */
@Slf4j
@SpringBootApplication
public class WlsGencodeApplication {
    
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext app = SpringApplication.run(WlsGencodeApplication.class, args);
        
        Environment env = app.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String appName = env.getProperty("spring.application.name");
        String path = String.valueOf(env.getProperty("server.servlet.context-path"));
        log.info("\n----------------------------------------------------------\n\t" +
                "Application " + appName + " is running! Access URLs:path:" + path + "\n\t" +
                "Health: \t\thttp://localhost:" + port + path + "/actuator/health" + "\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "\n\t" +
                "Doc文档: \thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");
    }
    
}
