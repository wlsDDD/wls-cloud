package cn.erectpine.common.redis.aspect;

import cn.erectpine.common.redis.RedisUtil;
import cn.erectpine.common.redis.annotation.CacheClear;
import cn.erectpine.common.redis.constant.CachePrefixEnum;
import cn.erectpine.common.web.util.AspectUtil;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Set;

import static cn.erectpine.common.redis.constant.CachePrefixEnum.format;

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
    
    @Around("@annotation(cn.erectpine.common.redis.annotation.CacheClear)")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed;
        try {
            CacheClear cacheClear = AspectUtil.getAnnotation(joinPoint, CacheClear.class);
            Set<String> keys = RedisUtil.scan(format(CachePrefixEnum.METHOD_CACHE.getPrefix(), cacheClear.cacheLevel().getLevelFunc().get(), cacheClear.value()));
            if (CollUtil.isNotEmpty(keys)) {
                RedisUtil.redisTemplate.delete(keys);
            }
        } catch (Exception e) {
            log.error("缓存清理失败 异常", e);
        } finally {
            proceed = joinPoint.proceed();
        }
        return proceed;
    }
    
}
