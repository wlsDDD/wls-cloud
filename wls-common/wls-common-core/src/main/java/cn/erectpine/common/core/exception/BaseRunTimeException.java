package cn.erectpine.common.core.exception;

import cn.erectpine.common.core.constant.GlobalConstants;
import cn.erectpine.common.core.enums.ActiveEnum;
import cn.erectpine.common.core.enums.CodeInfoEnum;
import cn.erectpine.common.core.util.pine.Pines;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常基类
 *
 * @author wls
 * @since 2021/1/13 14:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseRunTimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    /**
     * 所属模块-默认服务名
     */
    private String module = GlobalConstants.serviceName;
    /**
     * 项目运行环境
     */
    private ActiveEnum active = GlobalConstants.active;
    
    /**
     * 错误码
     */
    private String code;
    
    
    /**
     * 基本运行时异常
     * 构造方法-带code
     *
     * @param codeInfoEnum 代码味精枚举
     * @param params       参数个数
     */
    public BaseRunTimeException(CodeInfoEnum codeInfoEnum, Object... params) {
        super(codeInfoEnum.getInfo());
        codeInfoEnum.setInfo(params);
        this.code = codeInfoEnum.getCode();
    }
    
    /**
     * 获取过滤后的堆栈日志
     *
     * @return {@link StackTraceElement[]}
     */
    public StackTraceElement[] getSimpleStackTrace() {
        return Pines.getSimpleStackTrace(this, GlobalConstants.stackFilter);
    }
    
    public BaseRunTimeException() {
        super();
    }
    
    public BaseRunTimeException(String message) {
        super(message);
    }
    
    public BaseRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BaseRunTimeException(Throwable cause) {
        super(cause);
    }
    
    protected BaseRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
