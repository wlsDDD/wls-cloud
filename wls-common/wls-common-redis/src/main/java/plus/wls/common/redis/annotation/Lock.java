package plus.wls.common.redis.annotation;

import plus.wls.common.redis.enums.LookEnum;

import java.lang.annotation.*;

/**
 * '
 * 分布式锁注解
 *
 * @author wls
 * @since 2021/9/17 9:36
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Lock {
    
    /**
     * 锁类型
     *
     * @return {@link LookEnum}
     */
    LookEnum value() default LookEnum.WAIT;
    
}
