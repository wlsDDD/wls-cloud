package cn.erectpine.common.enums;

import lombok.Getter;

/**
 * 服务enum
 *
 * @Author wls
 * @Date 2021/4/3 16:07
 */
@Getter
public enum ServeEnum {
    
    /**
     * 系统服务
     */
    SYSTEM("wls-system"),
    DICT("wls-dict"),
    GATEWAY("wls-gateway"),
    
    ;
    
    private final String serveName;
    
    ServeEnum(String serveName) {
        this.serveName = serveName;
    }
    
}
