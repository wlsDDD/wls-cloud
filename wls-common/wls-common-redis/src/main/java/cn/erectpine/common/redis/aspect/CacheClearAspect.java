package cn.erectpine.common.redis.aspect;

import cn.erectpine.common.core.constant.GlobalConstants;
import cn.erectpine.common.redis.RedisUtil;
import cn.erectpine.common.redis.annotation.CacheClear;
import cn.erectpine.common.web.util.AspectUtil;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 缓存清理切面
 * 实现 {@link CacheClear}
 *
 * @author wls
 * @since 2021/10/11 17:51:40
 */
@Slf4j
@Aspect
@Component
public class CacheClearAspect {
    
    /**
     * 方法缓存前缀
     */
    private static final String CACHE_KEY = GlobalConstants.PROJECT_NAME + ":" + GlobalConstants.serviceName + ":" + GlobalConstants.active + ":" + "method-cache:";
    
    @Around("@annotation(cn.erectpine.common.redis.annotation.CacheClear)")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        CacheClear cacheClear = AspectUtil.getAnnotation(joinPoint, CacheClear.class);
        String cacheKey = CACHE_KEY + cacheClear.value() + "*";
        Set<Object> keys = RedisUtil.redisTemplate.keys(cacheKey);
        if (CollUtil.isNotEmpty(keys)) {
            RedisUtil.redisTemplate.delete(keys);
        }
        return joinPoint.proceed();
    }
    
    
}
