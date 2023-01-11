package plus.wls.common.redis.annotation;

import plus.wls.common.redis.enums.CacheLevelEnum;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * redis 缓存注解
 * 根据方法入参 缓存方法返回结果
 *
 * @author wls
 * @since 2021/10/8 17:45
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {
    
    /**
     * 用于缓存管理
     * 清理缓存时使用
     * {@link CacheClear}
     */
    Class<?> value() default Cache.class;
    
    /**
     * 缓存级别
     */
    CacheLevelEnum cacheLevel() default CacheLevelEnum.GLOBAL;
    
    /**
     * 缓存时长
     */
    long duration() default 20;
    
    /**
     * 时间单位
     */
    TimeUnit timeUnit() default TimeUnit.MINUTES;
    
}
