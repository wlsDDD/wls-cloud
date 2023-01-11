package plus.wls.common.log.annotation;

import java.lang.annotation.*;

/**
 * 日志忽略
 * 默认全忽略
 *
 * @author wls
 * @since 2021/01/15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogIgnore {
    
    /**
     * 默认忽略请求数据日志
     */
    boolean ignoreRequestData() default false;
    
    /**
     * 默认忽略响应数据日志
     */
    boolean ignoreResponseData() default false;
    
    /**
     * 默认忽略堆栈日志
     */
    boolean ignoreStacktrace() default false;
    
}