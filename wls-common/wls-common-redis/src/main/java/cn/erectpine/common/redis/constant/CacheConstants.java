package cn.erectpine.common.redis.constant;

/**
 * Redis缓存前缀
 *
 * @Author wls
 * @Date 2020/12/10 16:43
 */
public interface CacheConstants {
    
    /**
     * 验证码缓存前缀
     */
    String CAPTCHA_CODE = "USER:CAPTCHA:CODE:";
    
    /**
     * 用户token集中管理前缀
     */
    String USER_CONCENTRATED_TOKEN = "USER:TOKEN:";
    
}
