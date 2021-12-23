package cn.erectpine.common.core.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 *
 * @author wls
 * @since 2021/08/21 21:10:41
 */
public class PineThreadFactory implements ThreadFactory {
    
    private final AtomicInteger count = new AtomicInteger(0);
    
    private final String threadName;
    
    public PineThreadFactory(String threadName) {
        this.threadName = threadName;
    }
    
    @Override
    @SuppressWarnings("NullableProblems")
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName(threadName + "-" + count.addAndGet(1));
        return thread;
    }
    
}