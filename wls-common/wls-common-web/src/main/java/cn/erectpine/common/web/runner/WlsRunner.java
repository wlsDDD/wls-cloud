package cn.erectpine.common.web.runner;

import cn.erectpine.common.core.constant.GlobalConstants;
import cn.erectpine.common.core.enums.ActiveEnum;
import cn.erectpine.common.core.enums.CodeMsgEnum;
import cn.erectpine.common.core.thread.PineThreadPoolExecutor;
import cn.erectpine.common.web.context.Context;
import cn.erectpine.common.web.properties.WlsShareYml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动时-立即初始化公共资源
 *
 * @Author wls
 * @Date 2021/4/3 18:11
 */
@Component
public class WlsRunner implements CommandLineRunner {
    
    @Autowired WlsShareYml wlsShareYml;
    @Autowired PineThreadPoolExecutor pineThreadPoolExecutor;
    
    @Override
    public void run(String... args) {
        GlobalConstants.serviceName = wlsShareYml.getServiceName();
        GlobalConstants.active = wlsShareYml.getActive();
        GlobalConstants.stackFilter = wlsShareYml.getStackFilter();
        boolean isProd = ActiveEnum.prod.equals(GlobalConstants.active);
        CodeMsgEnum.UNKNOWN_ERROR.setMsg(isProd ? "服务器繁忙! 请稍后重试!" : "服务错误! 请联系开发人员!");
        Context.threadPool = pineThreadPoolExecutor;
    }
    
}
