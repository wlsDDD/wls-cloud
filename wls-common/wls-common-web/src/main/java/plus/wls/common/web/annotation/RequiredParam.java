package plus.wls.common.web.annotation;

import java.lang.annotation.*;

/**
 * 验证字段有效值注解
 *
 * @author wls
 * @since 2021/11/02 16:02:50
 */

@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RequiredParam {
    
    /**
     * 必传字段
     */
    String[] value();
    
    /**
     * 可选参数
     */
    String[] optionalParam() default "";
    
}
