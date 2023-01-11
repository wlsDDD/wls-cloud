package plus.wls.common.redis;

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
     * 过期时间 单位 秒
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
    
    /**
     * get
     *
     * @param key   key
     * @param clazz clazz
     * @param func  函数
     *
     * @return {@link V }
     *
     * @author wls
     * @date 2022-06-30 12:14:34
     * @since 1.0.0
     */
    public V get(K key, Class<V> clazz, Function<K, V> func) {
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
    
    /**
     * get
     *
     * @param key   key
     * @param clazz clazz
     *
     * @return {@link V }
     *
     * @author wls
     * @date 2022-06-30 12:14:49
     * @since 1.0.0
     */
    public V get(K key, Class<V> clazz) {
        String keyStr = JSONUtil.toJsonStr(key);
        String val = stringRedisTemplate.opsForValue().get(keyStr);
        return JSONUtil.toBean(val, clazz);
    }
    
    /**
     * set
     *
     * @param key key
     * @param val val
     *
     * @author wls
     * @date 2022-06-30 12:16:10
     * @since 1.0.0
     */
    public void set(K key, V val) {
        set(key, val, timeOut, TimeUnit.SECONDS);
    }
    
    /**
     * set
     *
     * @param key     key
     * @param val     val
     * @param timeOut 时间
     * @param unit    时间单位
     *
     * @author wls
     * @date 2022-06-30 12:16:21
     * @since 1.0.0
     */
    public void set(K key, V val, long timeOut, TimeUnit unit) {
        String keyStr = JSONUtil.toJsonStr(key);
        String valStr = JSONUtil.toJsonStr(val);
        stringRedisTemplate.opsForValue().set(keyStr, valStr, timeOut, unit);
    }
    
}
