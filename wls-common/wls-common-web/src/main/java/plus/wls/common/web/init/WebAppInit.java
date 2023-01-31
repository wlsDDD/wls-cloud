package plus.wls.common.web.init;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import plus.wls.common.core.constant.GlobalConstants;
import plus.wls.common.core.enums.ActiveEnum;
import plus.wls.common.core.enums.CodeInfoEnum;
import plus.wls.common.web.pojo.properties.WlsShareYml;
import plus.wls.common.web.thread.PineThreadPoolExecutor;

import javax.annotation.PostConstruct;

/**
 * 项目启动时-初始化公共资源
 *
 * @author wls
 * @since 2021/4/3 18:11
 */
@Slf4j
@Component
@AllArgsConstructor
public class WebAppInit {
    
    private WlsShareYml wlsShareYml;
    private PineThreadPoolExecutor pineThreadPoolExecutor;
    
    
    /**
     * 项目启动时自动执行此方法
     */
    @PostConstruct
    public void initContext() {
        // 初始化上下文环境
        GlobalConstants.serviceName = wlsShareYml.getServiceName();
        log.info("[服务名 -> 初始化] - [{}]", GlobalConstants.serviceName);
        GlobalConstants.active = wlsShareYml.getActive();
        log.info("[项目环境 -> 初始化] - [{}]", GlobalConstants.active);
        GlobalConstants.stackFilter = wlsShareYml.getStackFilter();
        log.info("[堆栈过滤配置 -> 初始化] - [{}]", GlobalConstants.stackFilter);
        CodeInfoEnum.UNKNOWN_ERROR.setInfo(ActiveEnum.prod.equals(GlobalConstants.active) ? "网络异常! 请稍后再试!" : "后端服务错误! ! 请携带<requestId>寻找相关开发人员!");
        log.info("[{} 环境异常提示语 -> 初始化] - {}", GlobalConstants.active, CodeInfoEnum.UNKNOWN_ERROR.getInfo());
    }
    
}
