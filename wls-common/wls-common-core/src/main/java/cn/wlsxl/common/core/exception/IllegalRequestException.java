package cn.wlsxl.common.core.exception;

import cn.wlsxl.common.core.enums.CodeInfoEnum;

/**
 * 非法请求异常
 *
 * @author wls
 * @since 2021/10/03 21:01:37
 */
public class IllegalRequestException extends BaseRunTimeException {
    private static final long serialVersionUID = 1L;
    
    /**
     * 基本运行时异常
     * 构造方法-带code
     *
     * @param codeInfoEnum 返回状态枚举
     * @param params       参数个数
     */
    public IllegalRequestException(CodeInfoEnum codeInfoEnum, Object... params) {
        super(codeInfoEnum, params);
    }
    
    public IllegalRequestException() {
        super();
    }
    
    public IllegalRequestException(String message) {
        super(message);
    }
    
    public IllegalRequestException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public IllegalRequestException(Throwable cause) {
        super(cause);
    }
    
    protected IllegalRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
