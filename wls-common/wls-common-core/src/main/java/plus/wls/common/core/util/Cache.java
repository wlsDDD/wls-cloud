package plus.wls.common.core.util;

import cn.hutool.cache.impl.CacheObj;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.lang.mutable.Mutable;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 缓存
 *
 * @author wls
 * @date 2022-06-22 10:02:12
 * @since 1.0.0
 */
public class Cache<K, V> extends TimedCache<K, V> {
    
    /**
     * 构造
     *
     * @param timeout 超时时长
     * @param unit    时间单位
     */
    public Cache(long timeout, TimeUnit unit) {
        super(unit.toSeconds(timeout));
    }
    
    /**
     * 构造
     *
     * @param timeout 超时（过期）时长，单位毫秒
     */
    public Cache(long timeout) {
        super(timeout);
    }
    
    /**
     * 构造
     *
     * @param timeout 过期时长
     * @param map     存储缓存对象的map
     */
    public Cache(long timeout, Map<Mutable<K>, CacheObj<K, V>> map) {
        super(timeout, map);
    }
    
    /**
     * 尝试从缓存中获取 无则写入缓存
     *
     * @return {@link V }
     *
     * @author wls
     * @date 2022-06-22 10:12:19
     * @since 1.0.0
     */
    public V computeIfAbsent(K key, Function<K, V> func) {
        V v;
        if ((v = get(key)) == null) {
            if ((v = func.apply(key)) != null) {
                put(key, v);
                return v;
            }
        }
        return v;
    }
    
}
