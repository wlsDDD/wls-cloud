package plus.wls.common.redis.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import plus.wls.common.core.constant.GlobalConstants;

/**
 * Redis缓存前缀
 *
 * @author wls
 * @since 2020/12/10 16:43
 */
@Getter
@AllArgsConstructor
public enum CachePrefixEnum {
    
    /**
     * 统一前缀缓存
     */
    CACHE_PREFIX(format(GlobalConstants.projectName, GlobalConstants.serviceName, GlobalConstants.active)),
    
    /**
     * 方法缓存前缀
     */
    METHOD_CACHE(format(CACHE_PREFIX, "method")),
    
    /**
     * 验证码缓存前缀
     */
    CAPTCHA_CODE("user:captcha:code"),
    
    /**
     * 用户token集中管理前缀
     */
    USER_CONCENTRATED_TOKEN("user:token"),
    
    ;
    
    private final String prefix;
    
    /**
     * 将参数 以:连接起来
     */
    public static String format(Object... params) {
        return StrUtil.format(StrUtil.removeSuffix(StrUtil.repeat("{}:", params.length), ":"), params);
    }
    
}
