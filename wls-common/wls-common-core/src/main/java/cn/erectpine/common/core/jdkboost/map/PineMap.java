package cn.erectpine.common.core.jdkboost.map;

import java.util.LinkedHashMap;

/**
 * 链式调用map
 *
 * @author wls
 * @since 2021/08/21 16:20:50
 */
public class PineMap<K, V> extends LinkedHashMap<K, V> {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 添加
     * 用法同Map中的put方法
     * 由于重写put方法返回类型不一致
     * 因此这里用add方法替代put方法
     *
     * @param key   关键
     * @param value 价值
     * @return {@link PineMap<>}
     */
    public PineMap<K, V> putItem(K key, V value) {
        super.put(key, value);
        return this;
    }
    
}
