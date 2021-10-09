package cn.erectpine.common.redis;

import cn.erectpine.common.core.function.FunctionSerializable;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis工具类
 *
 * @author wls
 * @since 2021/9/17 9:27
 */
@Slf4j
@Component
public class RedisUtil {
    
    /**
     * 自带序列化的客户端
     * 启动时自动初始化
     */
    public static StringRedisTemplate stringRedisTemplate;
    
    /**
     * redisson客户端
     * 启动时自动初始化
     */
    public static RedissonClient redissonClient;
    
    /**
     * 分布式锁
     *
     * @param function 函数
     * @param param    参数
     * @return {@link R}
     */
    @Deprecated
    public static <T, R> R distributedLock(FunctionSerializable<T, R> function, T param) {
        R proceed;
        RLock lock = RedisUtil.redissonClient.getLock("PROJECT_NAME::SERVER_NAME::DISTRIBUTED_LOCK::" + IdUtil.simpleUUID());
        try {
            lock.lock();
            proceed = function.apply(param);
        } catch (Throwable e) {
            log.error("[分布式锁 -> 加锁方法执行异常]");
            throw e;
        } finally {
            lock.unlock();
            log.info("[分布式锁 -> 正常释放]");
        }
        return proceed;
    }
    
}
