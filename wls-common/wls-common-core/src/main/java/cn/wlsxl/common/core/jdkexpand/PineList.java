package cn.wlsxl.common.core.jdkexpand;

import java.util.LinkedList;

/**
 * 链式调用list
 *
 * @author wls
 * @since 2021/08/21 16:20:34
 */
public class PineList<T> extends LinkedList<T> {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 提供链式调用add方法
     *
     * @param item 项
     *
     * @return {@link PineList<T>}
     */
    public PineList<T> addItem(T item) {
        super.add(item);
        return this;
    }
    
}
