package cn.erectpine.common.core.jdkboost.map;

import cn.erectpine.common.core.function.FunctionSerializable;
import cn.erectpine.common.core.util.LamUtil;

/**
 * 链式调用map
 *
 * @author wls
 */
public class PineStrMap<V> extends PineMap<String, V> {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 添加
     * 用法同Map中的put方法
     * 由于重写put方法返回类型不一致
     * 因此这里用add方法替代put方法
     *
     * @param key   关键
     * @param value 价值
     * @return {@link PineStrMap<V>}
     */
    @Override
    public PineStrMap<V> putItem(String key, V value) {
        super.putItem(key, value);
        return this;
    }
    
    /**
     * 添加
     * 用法同Map中的put方法
     * 由于重写put方法返回类型不一致
     * 因此这里用add方法替代put方法
     *
     * @param func  函数
     * @param value 价值
     * @return {@link PineStrMap<V>}
     */
    public <T, R> PineStrMap<V> putItem(FunctionSerializable<T, R> func, V value) {
        putItem(LamUtil.getFieldName(func), value);
        return this;
    }
    
}
