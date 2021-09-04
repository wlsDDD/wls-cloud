package cn.erectpine.common.core.config;

import cn.erectpine.common.core.thread.PineThreadPoolExecutor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程执行器配置
 *
 * @Author wls
 * @Date 2021/8/21 20:13
 */
@Data
@Accessors(chain = true)
@Configuration
public class PineThreadExecutorConfig {
    
    /**
     * 当前虚拟机可用cpu数
     */
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    /**
     * 当前虚拟机可用最大内存MB
     */
    private static final int MAX_MEMORY = (int) Runtime.getRuntime().maxMemory() / 1024 / 1024;
    
    /**
     * 核心线程数
     * 此处取系统cpu数/16中的较大值
     */
    private static final int CORE_POOL_SIZE = Math.max(16, AVAILABLE_PROCESSORS);
    /**
     * 最大线程数
     */
    private static final int MAXIMUM_POOL_SIZE = CORE_POOL_SIZE * 4;
    /**
     * 非核心线程生存时间
     */
    private static final long KEEP_ALIVE_TIME = 30;
    /**
     * 非核心线程生存时间单位：秒
     */
    private static final TimeUnit UNIT = TimeUnit.SECONDS;
    /**
     * 线程队列
     * 此处用虚拟机可用最大内存MB 防止OOM
     */
    private static final LinkedBlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>(1024);
    /**
     * 线程创建工厂
     */
    private static final ThreadFactory THREAD_FACTORY = new PineThreadFactory();
    /**
     * 线程池任务队列超过最大值之后的拒绝策略
     * 此处用既不抛弃任务也不抛出异常，直接使用主线程来执行此任务
     */
    private static final RejectedExecutionHandler HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();
    
    
    @Bean
    public PineThreadPoolExecutor threadPoolExecutor() {
        return new PineThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, UNIT, WORK_QUEUE, THREAD_FACTORY, HANDLER);
    }
    
}

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