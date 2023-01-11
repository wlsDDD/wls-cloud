package plus.wls.common.core.util;

import cn.hutool.core.lang.Assert;

import java.util.function.Supplier;

/**
 * 自定义断言
 *
 * @author wls
 * @since 2020/9/23 13:51
 */
public class Asserts extends Assert {
    
    /**
     * 断言count为0
     *
     * @param count     count
     * @param exception 异常
     *
     * @throws T t
     */
    public static <T extends Throwable> void isZero(Integer count, Supplier<T> exception) throws T {
        if (count != 0) {
            throw exception.get();
        }
    }
    
}
