package plus.wls.common.redis.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.springframework.stereotype.Component;
import plus.wls.common.core.constant.GlobalConstants;
import plus.wls.common.redis.RedisUtil;
import plus.wls.common.redis.annotation.Lock;
import plus.wls.common.web.context.Context;
import plus.wls.common.web.context.HttpContext;
import plus.wls.common.web.util.AspectUtil;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * 分布式锁注解实现
 *
 * @author wls
 * @since 2021/9/17 9:38
 */
@Slf4j
@Aspect
@Component
public class LockAspect {
    
    /**
     * 分布式锁前缀
     */
    private static final String LOCK_NAME = GlobalConstants.projectName + ":" + GlobalConstants.serviceName + ":" + GlobalConstants.active + ":" + "distributed-lock:";
    static Supplier<String> diyLookFunc = () -> Optional.ofNullable(HttpContext.getContext()).orElseGet(Context::new).getDiyDistributedLockKey();
    
    @Around("@annotation(distributedLock)")
    public Object around(final ProceedingJoinPoint joinPoint, Lock distributedLock) throws Throwable {
        String lockKey = LOCK_NAME + joinPoint.getSignature().getName() + ":" + DigestUtil.sha256Hex(AspectUtil.getMethodName(joinPoint));
        String diyDistributedLockKey = diyLookFunc.get();
        // 支持自定义扩展更细粒度的锁
        if (StrUtil.isNotBlank(diyDistributedLockKey)) {
            lockKey = lockKey + ":" + diyDistributedLockKey;
        }
        RLock lock = RedisUtil.redissonClient.getLock(lockKey);
        switch (distributedLock.value()) {
            case WAIT:
                return lock(joinPoint, lock);
            case TRY_LOCK:
                return tryLock(joinPoint, lock);
            default:
                log.error("[分布式锁 <> -> 匹配锁失败, 放弃执行代理方法]");
                return null;
        }
    }
    
    /**
     * 阻塞锁实现
     *
     * @param joinPoint 连接点
     *
     * @return {@link Object}
     */
    private Object lock(ProceedingJoinPoint joinPoint, RLock lock) throws Throwable {
        try {
            lock.lock();
            log.info("[分布式锁 <lock> -> 加锁成功] - {}", lock.getName());
            return joinPoint.proceed();
        } catch (Throwable e) {
            log.error("[分布式锁 <lock> -> 加锁方法执行异常] - {} - {}", lock.getName(), e);
            throw e;
        } finally {
            try {
                lock.unlock();
                log.info("[分布式锁 <lock> -> 释放正常] - {}", lock.getName());
            } catch (Throwable e) {
                log.error("[分布式锁 <lock> -> 释放异常] - {} - {}", lock.getName(), e);
            }
        }
    }
    
    /**
     * tryLock 实现
     *
     * @param joinPoint 连接点
     *
     * @return {@link Object}
     */
    private Object tryLock(ProceedingJoinPoint joinPoint, RLock lock) throws Throwable {
        if (lock.tryLock()) {
            try {
                log.info("[分布式锁 <tryLock> -> 加锁成功] - {}", lock.getName());
                return joinPoint.proceed();
            } catch (Throwable e) {
                log.error("[分布式锁 <tryLock> -> 加锁方法执行异常] - {} - {}", lock.getName(), e);
                throw e;
            } finally {
                try {
                    lock.unlock();
                    log.info("[分布式锁 <tryLock> -> 释放正常] - {}", lock.getName());
                } catch (Throwable e) {
                    log.error("[分布式锁 <tryLock> -> 释放异常] - {}", lock.getName(), e);
                }
            }
        }
        log.info("[分布式锁 <tryLock> -> 未获取到锁 放弃执行代理方法] - {}", lock.getName());
        return null;
    }
    
}
