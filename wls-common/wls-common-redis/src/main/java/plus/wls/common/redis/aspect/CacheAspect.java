package plus.wls.common.redis.aspect;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONConfig;
import plus.wls.common.redis.RedisUtil;
import plus.wls.common.redis.annotation.Cache;
import plus.wls.common.redis.enums.CachePrefixEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 缓存切面
 * 实现 {@link Cache}
 *
 * @author wls
 * @since 2021/10/08 17:49:41
 */
@Slf4j
@Aspect
@Component
public class CacheAspect {
    
    @Around("@annotation(cache)")
    public Object around(final ProceedingJoinPoint joinPoint, Cache cache) throws Throwable {
        String cacheKey;
        try {
            JSONConfig config = new JSONConfig().setOrder(true).setDateFormat(DatePattern.NORM_DATETIME_MS_PATTERN).setIgnoreError(true);
            String joinPointStr = new JSONArray(joinPoint.getArgs(), config).toJSONString(0);
            cacheKey = CachePrefixEnum.format(CachePrefixEnum.METHOD_CACHE.getPrefix(), cache.cacheLevel().getLevelFunc()
                                                                                             .get(), cache.value(), DigestUtil.md5Hex16(joinPointStr));
            Object result = RedisUtil.redisTemplate.opsForValue().get(cacheKey);
            if (ObjectUtil.isNotNull(result)) {
                return result;
            }
        } catch (Exception e) {
            log.error("缓存失败 前置异常 直接执行代理方法", e);
            return joinPoint.proceed();
        }
        Object proceed = joinPoint.proceed();
        try {
            RedisUtil.redisTemplate.opsForValue().set(cacheKey, proceed, cache.duration(), cache.timeUnit());
        } catch (Exception e) {
            log.error("缓存失败 后置异常 直接返回代理方法执行结果", e);
        }
        return proceed;
    }
    
}
