package cn.erectpine.common.redis.aspect;

import cn.erectpine.common.redis.RedisUtil;
import cn.erectpine.common.redis.annotation.CacheClear;
import cn.erectpine.common.web.context.HttpContext;
import cn.erectpine.common.web.util.AspectUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
    
    @Around("@annotation(cn.erectpine.common.redis.annotation.CacheClear)")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed;
        try {
            CacheClear cacheClear = AspectUtil.getAnnotation(joinPoint, CacheClear.class);
            String headerStr = JSONArray.toJSONString(ServletUtil.getHeaderMap(HttpContext.getContext().getRequest()), SerializerFeature.SortField);
            Set<String> keys = RedisUtil.scan(StrUtil.format("{}:{}:{}", CacheAspect.CACHE_PREFIX, cacheClear.value(), DigestUtil.md5Hex16(headerStr)));
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
