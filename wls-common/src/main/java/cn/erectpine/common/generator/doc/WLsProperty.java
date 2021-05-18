package cn.erectpine.common.generator.doc;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 用于获取bean属性注释
 *
 * @Author wls
 * @Date 2021/5/18 9:33
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WLsProperty {
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
