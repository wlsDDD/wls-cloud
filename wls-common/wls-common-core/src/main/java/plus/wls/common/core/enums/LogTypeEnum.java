package plus.wls.common.core.enums;

import lombok.Getter;

/**
 * 日志类型枚举
 *
 * @author wls
 * @since 2021/1/14 14:13
 */
@Getter
public enum LogTypeEnum {
    /**
     * 日志类型,分隔符
     */
    SUCCESS("\n\n=====================================================接口请求成功=================================================="),
    FAIL("\n\n=====================================================接口请求失败=================================================="),
    END("请求结束\n=========================================================END=======================================================\n"),
    
    ;
    
    private final String delimiter;
    
    LogTypeEnum(String delimiter) {
        this.delimiter = delimiter;
    }
}
