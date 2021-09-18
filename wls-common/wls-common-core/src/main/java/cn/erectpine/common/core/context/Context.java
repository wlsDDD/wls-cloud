package cn.erectpine.common.core.context;

import cn.erectpine.common.core.pojo.ApiLog;
import cn.erectpine.common.core.thread.PineThreadPoolExecutor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 请求上下文实体
 *
 * @Author wls
 * @Date 2021/4/14 10:42
 */
@Data
@Accessors(chain = true)
public class Context {
    
    
    /**
     * 接口日志
     */
    private ApiLog apiLog;
    /**
     * 线程池
     */
    public static PineThreadPoolExecutor threadPool;
    /**
     * 分布式锁-key
     * 用于实现更细粒度的锁
     * 如 用户锁
     */
    private String distributedLockKey;
    
}