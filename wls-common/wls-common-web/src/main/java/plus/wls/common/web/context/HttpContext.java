package plus.wls.common.web.context;


import plus.wls.common.core.pojo.ApiLog;

/**
 * 请求上下文环境
 *
 * @author wls
 * @since 2021/4/14 10:41
 */
public class HttpContext {
    
    /**
     * 上下文可继承线程变量
     */
    private static final InheritableThreadLocal<Context> INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();
    
    /**
     * 获取请求唯一id
     *
     * @return {@link String}
     */
    public static String getRequestId() {
        return getApiLog().getRequestId();
    }
    
    /**
     * 获得api日志
     *
     * @return {@link ApiLog}
     */
    public static ApiLog getApiLog() {
        return getContext().getApiLog();
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
    public static void setContext(Context context) {
        INHERITABLE_THREAD_LOCAL.set(context);
    }
    
    /**
     * 删除上下文
     */
    public static void removeContext() {
        INHERITABLE_THREAD_LOCAL.remove();
    }
    
}
