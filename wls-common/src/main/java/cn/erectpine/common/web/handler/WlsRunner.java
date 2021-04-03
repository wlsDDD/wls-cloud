package cn.erectpine.common.web.handler;

import cn.erectpine.common.constant.GlobalConstants;
import cn.erectpine.common.properties.WlsShareYml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动时初始化公共资源
 *
 * @Author wls
 * @Date 2021/4/3 18:11
 */
@Component
public class WlsRunner implements CommandLineRunner {
    
    @Autowired WlsShareYml wlsShareYml;
    
    @Override
    public void run(String... args) {
        GlobalConstants.serviceName = wlsShareYml.getServiceName();
        GlobalConstants.active = wlsShareYml.getActive();
        GlobalConstants.stackFilter = wlsShareYml.getStackFilter();
    }
    
}
