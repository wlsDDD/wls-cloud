package cn.erectpine.common.redis.aspect;

import cn.erectpine.common.core.constant.GlobalConstants;
import cn.erectpine.common.core.context.PineContext;
import cn.erectpine.common.core.util.pine.AspectUtil;
import cn.erectpine.common.redis.RedisUtil;
import cn.erectpine.common.redis.annotation.DistributedLock;
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
        DistributedLock distributedLock = AspectUtil.getAnnotation(joinPoint, DistributedLock.class);
        String methodName = AspectUtil.getMethodName(joinPoint);
        switch (distributedLock.value()) {
            case LOCK:
                return lock(joinPoint, methodName);
            case TRY_LOCK:
                return tryLock(joinPoint, methodName);
            default:
                log.error("[分布式锁 <> -> 匹配锁失败, 放弃执行代理方法]");
                return null;
        }
    }
    
    /**
     * 尝试锁实现
     *
     * @param joinPoint 连接点
     * @return {@link Object}
     */
    private Object tryLock(ProceedingJoinPoint joinPoint, String methodName) {
        Object proceed = null;
        RLock lock = getLock(joinPoint, methodName);
        if (lock.tryLock()) {
            try {
                log.info("[分布式锁 <tryLock> -> 加锁成功] - {} - {}", methodName, lock.getName());
                proceed = joinPoint.proceed();
            } catch (Throwable e) {
                log.error("[分布式锁 <tryLock> -> 加锁方法执行异常] - {} - {}", methodName, lock.getName(), e);
            } finally {
                try {
                    lock.unlock();
                    log.info("[分布式锁 <tryLock> -> 释放正常] - {} - {}", methodName, lock.getName());
                } catch (Throwable e) {
                    log.error("[分布式锁 <tryLock> -> 释放异常] - {} - {}", methodName, lock.getName(), e);
                }
            }
        } else {
            log.info("[分布式锁 <tryLock> -> 未获取到锁 放弃执行代理方式] - {} - {}", methodName, lock.getName());
        }
        return proceed;
    }
    
    /**
     * 阻塞锁实现
     *
     * @param joinPoint 连接点
     * @return {@link Object}
     */
    private Object lock(ProceedingJoinPoint joinPoint, String methodName) throws Throwable {
        Object proceed;
        RLock lock = getLock(joinPoint, methodName);
        ;
        try {
            lock.lock();
            log.info("[分布式锁 <lock> -> 加锁成功] - {} - {}", methodName, lock.getName());
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            if (null != lock) {
                log.error("[分布式锁 <lock> -> 加锁方法执行异常] - {} - {}", methodName, lock.getName(), e);
            }
            throw e;
        } finally {
            try {
                if (null != lock) {
                    lock.unlock();
                    log.info("[分布式锁 <lock> -> 释放正常] - {} - {}", methodName, lock.getName());
                }
            } catch (Throwable e) {
                log.error("[分布式锁 <lock> -> 释放异常] - {} - {}", methodName, lock.getName(), e);
            }
        }
        return proceed;
    }
    
    /**
     * 根据key获取锁锁
     *
     * @param joinPoint  连接点
     * @param methodName 方法名称
     * @return {@link RLock}
     */
    private RLock getLock(ProceedingJoinPoint joinPoint, String methodName) {
        String lockKey = LOCK_NAME + joinPoint.getSignature().getName() + ":" + DigestUtil.md5Hex(methodName);
        String distributedLockKey = PineContext.getContext().getDistributedLockKey();
        // 支持自定义扩展更细粒度的锁
        if (StrUtil.isNotBlank(distributedLockKey)) {
            lockKey = lockKey + ":" + distributedLockKey;
        }
        return RedisUtil.redissonClient.getLock(lockKey);
    }
    
}
