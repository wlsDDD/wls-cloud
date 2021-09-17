package cn.erectpine.common.redis.annotation;

import java.lang.annotation.*;

/**
 * '
 * 分布式锁注解
 *
 * @author wls
 * @date 2021/9/17 9:36
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {
    
}
