package cn.erectpine.common.util;

/**
 * 自定义断言
 *
 * @Author wls
 * @Date 2020/9/23 13:51
 */
public class Assert extends org.springframework.util.Assert {
    
    /**
     * 断言count为0
     *
     * @param count   count
     * @param message 异常消息描述
     * @throws RuntimeException 当count不为0时
     */
    public static void isZero(Integer count, String message) {
        if (count != 0) {
            throw new IllegalArgumentException(message);
        }
    }
    
}
