package cn.erectpine.common.web.exception;

import cn.erectpine.common.web.enums.CodeMsgEnum;

/**
 * 业务异常
 *
 * @Author wls
 * @Date 2021/4/3 18:42
 */
public class BusinessException extends BaseRunTimeException {
    private static final long serialVersionUID = 1L;
    
    /**
     * 业务异常
     * 构造方法-带code
     *
     * @param codeMsgEnum 代码味精枚举
     * @param params      参数个数
     */
    public BusinessException(CodeMsgEnum codeMsgEnum, Object... params) {
        super(codeMsgEnum, params);
    }
}
