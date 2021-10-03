package cn.erectpine.common.core.exception;

import cn.erectpine.common.core.enums.ActiveEnum;
import cn.erectpine.common.core.enums.CodeInfoEnum;

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
     * @param codeInfoEnum 代码味精枚举
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
    
    /**
     * 所属模块-默认服务名
     */
    @Override
    public String getModule() {
        return super.getModule();
    }
    
    /**
     * 项目运行环境
     */
    @Override
    public ActiveEnum getActive() {
        return super.getActive();
    }
    
    /**
     * 错误码
     */
    @Override
    public String getCode() {
        return super.getCode();
    }
    
    /**
     * 错误码
     *
     * @param code
     */
    @Override
    public void setCode(String code) {
        super.setCode(code);
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    
    @Override
    protected boolean canEqual(Object other) {
        return super.canEqual(other);
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    /**
     * 获取过滤后的堆栈日志
     *
     * @return {@link StackTraceElement[]}
     */
    @Override
    public StackTraceElement[] getSimpleStackTrace() {
        return super.getSimpleStackTrace();
    }
    
    /**
     * 项目运行环境
     *
     * @param active
     */
    @Override
    public void setActive(ActiveEnum active) {
        super.setActive(active);
    }
    
    /**
     * 所属模块-默认服务名
     *
     * @param module
     */
    @Override
    public void setModule(String module) {
        super.setModule(module);
    }
    
}
