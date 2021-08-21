package cn.erectpine.common.core.jdkboost.map;

import cn.erectpine.common.core.function.FunctionSerializable;

/**
 * 链式调用map
 */
public class PineStrObjMap extends PineStrMap<Object> {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 添加
     * 用法同Map中的put方法
     * 由于重写put方法返回类型不一致
     * 因此这里用add方法替代put方法
     *
     * @param key   关键
     * @param value 价值
     * @return {@link PineStrMap<>}
     */
    @Override
    public PineStrObjMap putItem(String key, Object value) {
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
     * @return {@link PineStrMap<>}
     */
    @Override
    public <T, R> PineStrObjMap putItem(FunctionSerializable<T, R> func, Object value) {
        super.putItem(func, value);
        return this;
    }
    
}