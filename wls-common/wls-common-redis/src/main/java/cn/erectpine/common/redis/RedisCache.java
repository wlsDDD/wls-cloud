package cn.erectpine.common.redis;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Redis 换出
 *
 * @author wls
 * @date 2022-06-22 11:30:02
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@Slf4j
public class RedisCache<K, V> {
    
    /**
     * RedisTemplate
     */
    private static StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
    /**
     * 过期时间
     */
    private long timeOut;
    
    /**
     * 构造
     *
     * @param timeOut 时间
     */
    public RedisCache(long timeOut) {
        this.timeOut = timeOut;
    }
    
    /**
     * 构造
     *
     * @param timeOut 时间
     * @param unit    时间单位
     */
    public RedisCache(long timeOut, TimeUnit unit) {
        this.timeOut = unit.toSeconds(timeOut);
    }
    
    public V getIfAbsent(K key, Class<V> clazz, Function<K, V> func) {
        V v;
        if ((v = get(key, clazz)) == null) {
            V newValue;
            if ((newValue = func.apply(key)) != null) {
                set(key, newValue);
                return newValue;
            }
        }
        return v;
    }
    
    public V get(K key, Class<V> clazz) {
        String keyStr = JSONUtil.toJsonStr(key);
        String val = stringRedisTemplate.opsForValue().get(keyStr);
        return JSONUtil.toBean(val, clazz);
    }
    
    public void set(K key, V val) {
        String keyStr = JSONUtil.toJsonStr(key);
        String valStr = JSONUtil.toJsonStr(val);
        stringRedisTemplate.opsForValue().set(keyStr, valStr, timeOut);
    }
    
}
