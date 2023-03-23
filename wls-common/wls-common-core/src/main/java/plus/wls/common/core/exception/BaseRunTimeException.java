package plus.wls.common.core.exception;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import plus.wls.common.core.constant.GlobalConstants;
import plus.wls.common.core.enums.ActiveEnum;
import plus.wls.common.core.enums.CodeInfoEnum;
import plus.wls.common.core.util.Pines;

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
    private Integer code;
    
    private CodeInfoEnum codeInfoEnum = CodeInfoEnum.UNKNOWN_ERROR;
    
    
    public BaseRunTimeException(String message, Object... params) {
        super(CodeInfoEnum.BUSINESS_ERROR.setInfo(StrUtil.format(message, params)).getInfo());
        CodeInfoEnum infoEnum = CodeInfoEnum.BUSINESS_ERROR.setInfo(StrUtil.format(message, params));
        this.codeInfoEnum = infoEnum;
        this.code = infoEnum.getCode();
    }
    
    /**
     * 基本运行时异常
     * 构造方法-带code
     *
     * @param codeInfoEnum 返回状态枚举
     * @param params       参数个数
     */
    public BaseRunTimeException(CodeInfoEnum codeInfoEnum, Object... params) {
        super(codeInfoEnum.getInfo());
        codeInfoEnum.setInfo(params);
        this.code = codeInfoEnum.getCode();
        this.codeInfoEnum = codeInfoEnum;
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
    
    /**
     * 获取过滤后的堆栈日志
     *
     * @return {@link StackTraceElement[]}
     */
    public StackTraceElement[] getSimpleStackTrace() {
        return Pines.toSimpleStackTrace(this, GlobalConstants.basePackage);
    }
    
}
