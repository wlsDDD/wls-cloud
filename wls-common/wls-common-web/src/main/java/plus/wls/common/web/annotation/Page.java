package plus.wls.common.web.annotation;

import java.lang.annotation.*;

/**
 * 分页注解 封装分页逻辑
 *
 * @author wls
 * @since 2021/5/31 9:23
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Page {
    
    /**
     * 页面num
     *
     * @return int
     */
    int defaultPageNum() default 1;
    
    /**
     * 页面size
     *
     * @return int
     */
    int defaultPageSize() default 20;
    
}
