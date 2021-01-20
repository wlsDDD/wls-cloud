package cn.erectpine.common.web.exception;

import cn.erectpine.common.enums.CodeMsgEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义业务类异常
 *
 * @Author wls
 * @Date 2021/1/13 14:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends BaseException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public BusinessException() {
        super();
    }
    
    /**
     * 构造方法-带code
     *
     * @param codeMsgEnum {@link CodeMsgEnum}
     */
    public BusinessException(CodeMsgEnum codeMsgEnum) {
        super(codeMsgEnum);
    }
    
    /**
     * 构造方法-带code和module
     *
     * @param codeMsgEnum {@link CodeMsgEnum}
     * @param module      module
     */
    public BusinessException(CodeMsgEnum codeMsgEnum, String module) {
        super(codeMsgEnum, module);
    }
    
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BusinessException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new runtime exception with the specified cause and a
     * detail message of <tt>(cause==null ? null : cause.toString())</tt>
     * (which typically contains the class and detail message of
     * <tt>cause</tt>).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.4
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }
    
    /**
     * Constructs a new runtime exception with the specified detail
     * message, cause, suppression enabled or disabled, and writable
     * stack trace enabled or disabled.
     *
     * @param message            the detail message.
     * @param cause              the cause.  (A {@code null} value is permitted,
     *                           and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression  whether or not suppression is enabled
     *                           or disabled
     * @param writableStackTrace whether or not the stack trace should
     *                           be writable
     * @since 1.7
     */
    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
