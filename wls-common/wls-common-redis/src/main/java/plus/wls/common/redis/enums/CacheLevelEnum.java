package plus.wls.common.redis.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import plus.wls.common.web.context.HttpContext;

import java.util.function.Supplier;

/**
 * 缓存级别枚举
 * 默认支持三种级别缓存
 *
 * @author wls
 * @date 2022-05-23 09:38:15
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum CacheLevelEnum {
    
    /**
     * 用户
     */
    USER(() -> HttpContext.getContext().getUserInfo().getName()),
    /**
     * 租户
     */
    TENANT(() -> HttpContext.getContext().getUserInfo().getName()),
    /**
     * 全局
     */
    GLOBAL(() -> HttpContext.getContext().getUserInfo().getName()),
    /**
     * 可自定义扩展
     */
    DIY(() -> "DIY"),
    
    ;
    
    private Supplier<String> levelFunc;
    
    /**
     * 配置自定义缓存级别
     *
     * @param level 缓存级别
     */
    private void setDiy(Supplier<String> level) {
        this.levelFunc = level;
    }
    
}
