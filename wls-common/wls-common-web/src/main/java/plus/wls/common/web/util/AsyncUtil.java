package plus.wls.common.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plus.wls.common.web.thread.PineThreadPoolExecutor;

/**
 * 异步工具类
 *
 * @author wls
 * @since 2021/10/13 15:55:02
 */
@Component
public class AsyncUtil {
    
    public static PineThreadPoolExecutor threadPoolExecutor;
    
    public static void async(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
        // Context.threadPool.execute(runnable);
    }
    
    @Autowired
    public void setThreadPoolExecutor(PineThreadPoolExecutor threadPoolExecutor) {
        AsyncUtil.threadPoolExecutor = threadPoolExecutor;
    }
    
}
