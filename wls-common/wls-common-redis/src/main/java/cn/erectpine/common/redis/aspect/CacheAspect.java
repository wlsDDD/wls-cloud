package cn.erectpine.common.redis.aspect;

import cn.erectpine.common.core.constant.GlobalConstants;
import cn.erectpine.common.redis.RedisUtil;
import cn.erectpine.common.redis.annotation.Cache;
import cn.erectpine.common.web.context.HttpContext;
import cn.erectpine.common.web.util.AspectUtil;
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

import javax.servlet.http.HttpServletRequest;
import java.util.function.Supplier;

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
    public static final String CACHE_PREFIX = GlobalConstants.PROJECT_NAME + ":" + GlobalConstants.serviceName + ":" + GlobalConstants.active + ":" + "method";
    /**
     * 用户唯一标识
     */
    static Supplier<String> userId = () -> "{user_id}";
    
    @Around("@annotation(cn.erectpine.common.redis.annotation.Cache)")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        Cache cache = AspectUtil.getAnnotation(joinPoint, Cache.class);
        String cacheKey;
        try {
            HttpServletRequest request = HttpContext.getContext().getRequest();
            String joinPointStr = JSONArray.toJSONString(joinPoint.getArgs(), SerializerFeature.SortField);
            String paramStr = JSONArray.toJSONString(ServletUtil.getParamMap(request), SerializerFeature.SortField);
            String headerStr = JSONArray.toJSONString(ServletUtil.getHeaderMap(request), SerializerFeature.SortField);
            cacheKey = StrUtil.format("{}:{}:{}:{}", CACHE_PREFIX, cache.value(), DigestUtil.md5Hex16(headerStr), DigestUtil.md5Hex16(joinPointStr + paramStr));
            Object result = RedisUtil.redisTemplate.opsForValue().get(cacheKey);
            if (result != null) {
                return result;
            }
        } catch (Exception e) {
            log.error("缓存失败 前置异常", e);
            return joinPoint.proceed();
        }
        Object proceed = joinPoint.proceed();
        try {
            RedisUtil.redisTemplate.opsForValue().set(cacheKey, proceed, cache.duration(), cache.timeUnit());
        } catch (Exception e) {
            log.error("缓存失败 后置异常", e);
        }
        return proceed;
    }
    
}
