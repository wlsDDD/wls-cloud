package plus.wls.common.web.mask;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据屏蔽规则枚举
 *
 * @author wls
 * @since 2023-04-26
 */
@Getter
@AllArgsConstructor
public enum DataMaskRuleEnum {
    
    
    ;
    /**
     * 要脱敏的属性名
     */
    private final String fieldName;
}
