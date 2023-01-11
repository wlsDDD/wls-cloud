package plus.wls.common.log.annotation;


import plus.wls.common.core.enums.BusinessEnum;

import java.lang.annotation.*;

/**
 * 自定义记录接口日志注解
 *
 * @author wls
 * @since 2021/01/15 17:41:58
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    
    /**
     * 请求数据类型
     */
    BusinessEnum value() default BusinessEnum.OTHER;
    
    /**
     * 模块
     */
    String title() default "";
    
}
