package cn.erectpine.common.core.config;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 *
 * @author wls
 * @date 2021/08/21 21:10:41
 */
class PineThreadFactory implements ThreadFactory {
    
    private final AtomicInteger count = new AtomicInteger(0);
    
    @Override
    @SuppressWarnings("NullableProblems")
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("pine-thread-" + count.addAndGet(1));
        return thread;
    }
    
}