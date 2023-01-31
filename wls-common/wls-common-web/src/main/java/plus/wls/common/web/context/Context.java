package plus.wls.common.web.context;

import cn.hutool.system.UserInfo;
import lombok.Data;
import lombok.experimental.Accessors;
import plus.wls.common.core.pojo.ApiLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求上下文实体
 *
 * @author wls
 * @since 2021/4/14 10:42
 */
@Data
@Accessors(chain = true)
public class Context {
    
    /**
     * 线程池
     */
    // public static final PineThreadPoolExecutor threadPool = SpringUtil.getBean(PineThreadPoolExecutor.class);
    /**
     * http 请求对象
     */
    private HttpServletRequest request;
    /**
     * http 响应对象
     */
    private HttpServletResponse response;
    /**
     * 接口日志
     */
    private ApiLog apiLog;
    /**
     * 分布式锁-key
     * 用于实现更细粒度的锁
     * 如 用户锁
     */
    private String diyDistributedLockKey;
    /**
     * 用户信息
     */
    private UserInfo userInfo;
    
}