package cn.erectpine.common.redis.annotation;

import cn.erectpine.common.redis.constant.LookEnum;

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
public @interface DistributedLock {
    
    LookEnum value() default LookEnum.LOCK;
    
}
