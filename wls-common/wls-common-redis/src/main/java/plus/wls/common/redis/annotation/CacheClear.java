package plus.wls.common.redis.annotation;

import plus.wls.common.redis.enums.CacheLevelEnum;

import java.lang.annotation.*;

/**
 * 缓存清除
 *
 * @author wls
 * @since 2021/10/11 17:49:58
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheClear {
    
    /**
     * 用于缓存管理
     * 清理缓存时使用
     * {@link Cache}
     */
    Class<?> value() default Cache.class;
    
    /**
     * 缓存级别
     */
    CacheLevelEnum cacheLevel() default CacheLevelEnum.GLOBAL;
    
}
