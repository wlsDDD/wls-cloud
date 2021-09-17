package cn.erectpine.common.web.runner;

import cn.erectpine.common.core.constant.GlobalConstants;
import cn.erectpine.common.core.context.Context;
import cn.erectpine.common.core.enums.ActiveEnum;
import cn.erectpine.common.core.enums.CodeMsgEnum;
import cn.erectpine.common.core.thread.PineThreadPoolExecutor;
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
        log.info("[线程池 -> 初始化] - [{}]", Context.threadPool);
        Context.redis = redisTemplate;
        log.info("[redis -> 初始化] - [{}]", Context.redis);
    }
    
    /**
     * 初始化全局变量
     */
    @PostConstruct
    public void initGlobalConstants() {
        GlobalConstants.serviceName = wlsShareYml.getServiceName();
        log.info("[服务名 -> 初始化] - [{}]", GlobalConstants.serviceName);
        GlobalConstants.active = wlsShareYml.getActive();
        log.info("[项目环境 -> 初始化] - [{}]", GlobalConstants.active);
        GlobalConstants.stackFilter = wlsShareYml.getStackFilter();
        log.info("[堆栈过滤配置 -> 初始化] - [{}]", GlobalConstants.stackFilter);
        CodeMsgEnum.FAIL_UNKNOWN_ERROR.setInfo(ActiveEnum.prod.equals(GlobalConstants.active) ? "网络异常! 请稍后再试!" : "后端服务错误! ! 请携带<requestId>寻找相关开发人员!");
        log.info("[{}环境兜底提示语 -> 初始化] - [{}]", GlobalConstants.active, CodeMsgEnum.FAIL_UNKNOWN_ERROR.getInfo());
    }
    
}