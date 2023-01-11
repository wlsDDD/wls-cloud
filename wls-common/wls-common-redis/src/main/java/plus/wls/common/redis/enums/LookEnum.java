package plus.wls.common.redis.enums;

/**
 * 看枚举
 * 获取锁方式
 *
 * @author wls
 * @date 2021/09/20 23:06:43
 * @since 2021/9/19 12:25
 */
public enum LookEnum {
    /**
     * 阻塞锁，获取不到锁，阻塞当前线程获取锁
     */
    WAIT,
    /**
     * 尝试获取一次锁，获取不到则放弃获取锁
     */
    TRY_LOCK,
    
    ;
    
}
