package plus.wls.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * 封装 spring redis 工具类
 *
 * @author wls
 * @since 2020/9/29 16:35
 */
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class RedisService {
    
    @Autowired
    public RedisTemplate redisTemplate;
    
    
    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间单位
     *
     * @return 缓存的对象
     */
    public <T> ValueOperations<String, T> set(String key, T value, Integer timeout, TimeUnit timeUnit) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value, timeout, timeUnit);
        return operation;
    }
    
    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param duration 时间差
     *
     * @return 缓存的对象
     */
    public <T> ValueOperations<String, T> set(String key, T value, Duration duration) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value, duration);
        return operation;
    }
    
    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     *
     * @return 缓存键值对应的数据
     */
    public <T> T get(String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }
    
    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     *
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }
    
    /**
     * 判断key是否存在
     *
     * @param key Redis键
     */
    public boolean hasKey(final String key) {
        return redisTemplate.hasKey(key);
    }
    
    /**
     * 删除单个对象
     *
     * @param key Redis键
     */
    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }
    
}
