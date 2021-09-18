package cn.erectpine.common.core.context;


import cn.erectpine.common.core.pojo.ApiLog;
import cn.hutool.core.util.IdUtil;

/**
 * 请求上下文环境
 *
 * @author wls
 * @since 2021/4/14 10:41
 */
public class PineContext {
    
    /**
     * 上下文可继承线程变量
     */
    private static final InheritableThreadLocal<Context> INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();
    
    /**
     * 获得api日志
     *
     * @return {@link ApiLog}
     */
    public static ApiLog getApiLog() {
        return getContext().getApiLog();
    }
    
    /**
     * 获取请求唯一id
     *
     * @return {@link String}
     */
    public static String getRequestId() {
        return getApiLog().getRequestId();
    }
    
    /**
     * 获得上下文
     *
     * @return {@link Context}
     */
    public static Context getContext() {
        return INHERITABLE_THREAD_LOCAL.get();
    }
    
    /**
     * 设置上下文
     */
    public static void setContext() {
        INHERITABLE_THREAD_LOCAL.set(new Context().setApiLog(new ApiLog().setRequestId(IdUtil.simpleUUID())));
    }
    
    /**
     * 删除上下文
     */
    public static void removeContext() {
        INHERITABLE_THREAD_LOCAL.remove();
    }
    
}
