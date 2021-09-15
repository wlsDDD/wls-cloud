package cn.erectpine.common.web.runner;

import cn.erectpine.common.core.constant.GlobalConstants;
import cn.erectpine.common.core.enums.ActiveEnum;
import cn.erectpine.common.core.enums.CodeMsgEnum;
import cn.erectpine.common.core.thread.PineThreadPoolExecutor;
import cn.erectpine.common.web.context.Context;
import cn.erectpine.common.web.properties.WlsShareYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 项目启动时-初始化公共资源
 *
 * @Author wls
 * @Date 2021/4/3 18:11
 */
@Slf4j
@Component
public class WebAppInit {
    
    @Autowired WlsShareYml wlsShareYml;
    @Autowired PineThreadPoolExecutor pineThreadPoolExecutor;
    @Autowired StringRedisTemplate redisTemplate;
    
    
    /**
     * 初始化上下文环境
     */
    @PostConstruct
    public void initContext() {
        Context.threadPool = pineThreadPoolExecutor;
        Context.redis = redisTemplate;
    }
    
    /**
     * 初始化全局变量
     */
    @PostConstruct
    public void initGlobalConstants() {
        GlobalConstants.serviceName = wlsShareYml.getServiceName();
        GlobalConstants.active = wlsShareYml.getActive();
        GlobalConstants.stackFilter = wlsShareYml.getStackFilter();
    }
    
    /**
     * 初始化兜底提示信息
     */
    @PostConstruct
    public void initCodeMsg() {
        CodeMsgEnum.FAIL_UNKNOWN_ERROR.setMsg(ActiveEnum.prod.equals(GlobalConstants.active) ? "网络异常! 请稍后再试!" : "后端服务错误! ! 请携带<requestId>寻找相关开发人员!");
    }
    
}
