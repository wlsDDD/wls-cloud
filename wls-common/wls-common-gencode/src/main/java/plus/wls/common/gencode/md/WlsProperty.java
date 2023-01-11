package plus.wls.common.gencode.md;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 用于获取bean属性注释
 *
 * @author wls
 * @since 2021/5/18 9:33
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WlsProperty {
    /**
     * 参数说明
     *
     * @return {@link String}
     */
    String value() default "";
    
    /**
     * 是否必传
     *
     * @return boolean
     */
    boolean required() default false;
    
}
