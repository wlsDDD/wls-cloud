package cn.erectpine.common.redis.annotation;

import java.lang.annotation.*;

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

}
