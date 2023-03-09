package plus.wls.common.redis.aspect;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import plus.wls.common.redis.RedisUtil;
import plus.wls.common.redis.annotation.CacheClear;
import plus.wls.common.redis.enums.CachePrefixEnum;

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
    
    @Around("@annotation(cacheClear)")
    public Object around(final ProceedingJoinPoint joinPoint, CacheClear cacheClear) throws Throwable {
        Object proceed;
        try {
            Set<String> keys = RedisUtil.scan(CachePrefixEnum.format(CachePrefixEnum.METHOD_CACHE.getPrefix(), cacheClear.cacheLevel().getLevelFunc()
                                                                                                                         .get(), cacheClear.value()));
            if (CollUtil.isNotEmpty(keys)) {
                RedisUtil.redis.delete(keys);
            }
        } catch (Exception e) {
            log.error("缓存清理失败 异常", e);
        } finally {
            proceed = joinPoint.proceed();
        }
        return proceed;
    }
    
}
