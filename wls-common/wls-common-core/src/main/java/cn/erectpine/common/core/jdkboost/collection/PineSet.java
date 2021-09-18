package cn.erectpine.common.core.jdkboost.collection;

import java.util.LinkedHashSet;

/**
 * 链式调用set
 *
 * @author wls
 * @since 2021/08/21 16:20:44
 */
public class PineSet<T> extends LinkedHashSet<T> {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 提供链式调用add方法
     *
     * @param item 项
     * @return {@link PineList<T>}
     */
    public PineSet<T> addItem(T item) {
        super.add(item);
        return this;
    }
    
}
