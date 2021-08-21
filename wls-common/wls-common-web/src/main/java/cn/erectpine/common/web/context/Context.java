package cn.erectpine.common.web.context;

import cn.erectpine.common.core.thread.PineThreadPoolExecutor;
import cn.erectpine.common.web.pojo.ApiLog;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 上下文实体
 *
 * @Author wls
 * @Date 2021/4/14 10:42
 */
@Data
@Accessors(chain = true)
public class Context {
    
    /**
     * 线程池
     */
    public static PineThreadPoolExecutor threadPool;
    /**
     * 接口日志
     */
    private ApiLog apiLog;
    
}