package plus.wls.common.core.exception;

import plus.wls.common.core.enums.CodeInfoEnum;

/**
 * 请求头异常
 *
 * @author wls
 * @since 2021/10/03 14:53:05
 */
public class RequestHeaderException extends BaseRunTimeException {
    private static final long serialVersionUID = 1L;
    
    /**
     * 基本运行时异常
     * 构造方法-带code
     *
     * @param codeInfoEnum 返回状态枚举
     * @param params       参数个数
     */
    public RequestHeaderException(CodeInfoEnum codeInfoEnum, Object... params) {
        super(codeInfoEnum, params);
    }
    
    public RequestHeaderException() {
        super();
    }
    
    public RequestHeaderException(String message) {
        super(message);
    }
    
    public RequestHeaderException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public RequestHeaderException(Throwable cause) {
        super(cause);
    }
    
    protected RequestHeaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
