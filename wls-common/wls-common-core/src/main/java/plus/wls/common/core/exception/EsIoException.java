package plus.wls.common.core.exception;

import plus.wls.common.core.enums.CodeInfoEnum;

/**
 * es io异常
 *
 * @author wls
 * @since 2021/10/21 17:05:18
 */
public class EsIoException extends BaseRunTimeException {
    private static final long serialVersionUID = 1L;
    
    public EsIoException() {
        this(CodeInfoEnum.ES_SERVER_IO_ERROR);
    }
    
    /**
     * 基本运行时异常
     * 构造方法-带code
     *
     * @param codeInfoEnum 返回状态枚举
     * @param params       参数个数
     */
    public EsIoException(CodeInfoEnum codeInfoEnum, Object... params) {
        super(codeInfoEnum, params);
    }
    
    public EsIoException(String message) {
        this(CodeInfoEnum.ES_SERVER_IO_ERROR, message);
    }
    
    public EsIoException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public EsIoException(Throwable cause) {
        super(cause);
    }
    
    protected EsIoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
