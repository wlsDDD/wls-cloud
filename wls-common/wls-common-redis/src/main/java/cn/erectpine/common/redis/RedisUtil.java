package cn.erectpine.common.redis;

import cn.erectpine.common.core.function.FunctionSerializable;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Redis工具类
 *
 * @author wls
 * @since 2021/9/17 9:27
 */
@Slf4j
public class RedisUtil {
    
    /**
     * redis客户端
     * 启动时自动初始化
     */
    public static final RedisTemplate<String, Object> redisTemplate;
    /**
     * 自带序列化的客户端
     * 启动时自动初始化
     */
    public static final StringRedisTemplate stringRedisTemplate;
    
    /**
     * redisson客户端
     * 启动时自动初始化
     */
    public static final RedissonClient redissonClient;
    
    static {
        redisTemplate = SpringUtil.getBean(RedisTemplate.class);
        stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        redissonClient = SpringUtil.getBean(RedissonClient.class);
    }
    
    /**
     * 分布式锁
     *
     * @param function 函数
     * @param param    参数
     *
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
    
    /**
     * 获取指定前缀下的所有key
     * 替代keys
     *
     * @param matchKey 匹配前缀
     *
     * @return {@link Set}<{@link String}>
     */
    public static Set<String> scan(String matchKey) {
        return redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> keysTmp = new HashSet<>();
            Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match("*" + matchKey + "*").count(1000).build());
            while (cursor.hasNext()) {
                keysTmp.add(new String(cursor.next()));
            }
            return keysTmp;
        });
    }
    
}
