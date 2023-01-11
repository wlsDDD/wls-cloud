package plus.wls.common.core.exception;


import plus.wls.common.core.enums.CodeInfoEnum;

/**
 * 业务异常
 *
 * @author wls
 * @since 2021/4/3 18:42
 */
public class BusinessException extends BaseRunTimeException {
    private static final long serialVersionUID = 1L;
    
    /**
     * 业务异常
     * 构造方法-带code
     *
     * @param codeInfoEnum 返回状态枚举
     * @param params       参数个数
     */
    public BusinessException(CodeInfoEnum codeInfoEnum, Object... params) {
        super(codeInfoEnum, params);
    }
    
    public BusinessException() {
        super();
    }
    
    public BusinessException(String message) {
        super(message);
    }
    
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BusinessException(Throwable cause) {
        super(cause);
    }
    
    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
