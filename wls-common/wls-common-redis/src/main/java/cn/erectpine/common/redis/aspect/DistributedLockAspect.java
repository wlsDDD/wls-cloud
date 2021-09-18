package cn.erectpine.common.redis.aspect;

import cn.erectpine.common.core.constant.GlobalConstants;
import cn.erectpine.common.core.context.PineContext;
import cn.erectpine.common.core.util.pine.AspectUtil;
import cn.erectpine.common.redis.RedisUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.springframework.stereotype.Component;

/**
 * 分布式锁注解实现
 *
 * @author wls
 * @since 2021/9/17 9:38
 */
@Slf4j
@Aspect
@Component
public class DistributedLockAspect {
    
    /**
     * 分布式锁前缀
     */
    private static final String LOCK_NAME = GlobalConstants.PROJECT_NAME + ":" + GlobalConstants.serviceName + ":" + GlobalConstants.active + ":" + "distributed-lock:";
    
    @Around("@annotation(cn.erectpine.common.redis.annotation.DistributedLock)")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed;
        RLock lock = null;
        String methodName = AspectUtil.getMethodName(joinPoint);
        try {
            String lockKey = LOCK_NAME + joinPoint.getSignature().getName() + ":" + DigestUtil.md5Hex(methodName);
            String distributedLockKey = PineContext.getContext().getDistributedLockKey();
            // 支持自定义扩展更细粒度的锁
            if (StrUtil.isNotBlank(distributedLockKey)) {
                lockKey = lockKey + ":" + distributedLockKey;
            }
            lock = RedisUtil.redissonClient.getLock(lockKey);
            lock.lock();
            log.info("[分布式锁 -> 加锁成功] - {} - {}", methodName, lock.getName());
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            if (null != lock) {
                log.error("[分布式锁 -> 加锁方法执行异常] - {} - {}", methodName, lock.getName(), e);
            }
            throw e;
        } finally {
            try {
                if (null != lock) {
                    lock.unlock();
                    log.info("[分布式锁 -> 释放正常] - {} - {}", methodName, lock.getName());
                }
            } catch (Throwable e) {
                log.error("[分布式锁 -> 释放异常] - {} - {}", methodName, lock.getName(), e);
            }
        }
        return proceed;
    }
    
}
