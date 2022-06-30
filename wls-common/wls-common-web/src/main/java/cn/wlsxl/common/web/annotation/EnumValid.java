package cn.wlsxl.common.web.annotation;

import cn.wlsxl.common.web.valid.ParamValid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 验证字段有效值注解
 *
 * @author wls
 * @since 2021/11/02 16:02:50
 */

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {ParamValid.class})
public @interface EnumValid {
    /**
     * 默认错误消息
     */
    String message() default "必须为指定值";
    
    /**
     * 枚举类 默认序列号
     */
    Class<? extends Enum<?>> value();
    
    /**
     * 字段名
     */
    String fieldName() default "ordinal";
    
    /**
     * 分组
     */
    Class<?>[] groups() default {};
    
    /**
     * 负载
     */
    Class<? extends Payload>[] payload() default {};
    
}
