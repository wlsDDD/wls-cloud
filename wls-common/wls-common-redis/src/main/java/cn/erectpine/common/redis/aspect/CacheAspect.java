package cn.erectpine.common.redis.aspect;

import cn.erectpine.common.core.constant.GlobalConstants;
import cn.erectpine.common.redis.RedisUtil;
import cn.erectpine.common.redis.annotation.Cache;
import cn.erectpine.common.web.util.AspectUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
    
    /**
     * 方法缓存前缀
     */
    private static final String CACHE_KEY = GlobalConstants.PROJECT_NAME + ":" + GlobalConstants.serviceName + ":" + GlobalConstants.active + ":" + "method-cache:";
    
    
    @Around("@annotation(cn.erectpine.common.redis.annotation.Cache)")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        Cache cache = AspectUtil.getAnnotation(joinPoint, Cache.class);
        String cacheKey = CACHE_KEY + cache.value() + ":" + "用户唯一标识信息" + ":" +
                DigestUtil.md5Hex16(JSONArray.toJSONString(joinPoint.getArgs(), SerializerFeature.SortField));
        Object result = RedisUtil.redisTemplate.opsForValue().get(cacheKey);
        if (result != null) {
            return result;
        }
        Object proceed = joinPoint.proceed();
        RedisUtil.redisTemplate.opsForValue().set(cacheKey, proceed, cache.duration(), cache.timeUnit());
        return proceed;
    }
    
}
