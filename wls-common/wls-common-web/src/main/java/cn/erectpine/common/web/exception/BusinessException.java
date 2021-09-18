package cn.erectpine.common.web.exception;


import cn.erectpine.common.core.enums.CodeInfoEnum;

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
     * @param codeInfoEnum 代码味精枚举
     * @param params       参数个数
     */
    public BusinessException(CodeInfoEnum codeInfoEnum, Object... params) {
        super(codeInfoEnum, params);
    }
}
