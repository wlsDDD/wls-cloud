package cn.erectpine.common.redis.constant;

/**
 * 获取锁方式
 *
 * @author wls
 * @since 2021/9/19 12:25
 */
public enum LookEnum {
    /**
     * 阻塞锁，获取不到锁，阻塞当前线程知道获取到锁
     */
    LOCK,
    /**
     * 尝试获取一次锁，获取不到则放弃获取锁
     */
    TRY_LOCK,
    
    ;
    
}
