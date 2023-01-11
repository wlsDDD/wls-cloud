package plus.wls.common.core.enums;

import lombok.Getter;

/**
 * 服务enum
 *
 * @author wls
 * @since 2021/4/3 16:07
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
