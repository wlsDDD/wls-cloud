package cn.erectpine.common.enums;


import cn.erectpine.common.constant.HttpStatus;
import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * 异常枚举
 *
 * @author wls
 * @date 2021/01/13
 */
@Getter
public enum CodeMsgEnum {
    
    /**
     * 成功返回信息code&msg
     */
    SUCCESS(HttpStatus.SUCCESS, "success"),
    ERROR(HttpStatus.ERROR, "fail"),
    /**
     * 统一验证参数错误
     */
    ARG_VERIFY_ERROR(-1, "统一验证参数不合法, 请检查参数后重试! 以下信息仅供参考 \r\n"),
    UNKNOWN_DEV_ERROR(500, "服务错误! 请联系开发人员!"),
    UNKNOWN_PROD_ERROR(500, "服务器繁忙! 请稍后重试!"),
    DATA_SAVE_ERROR(5001, "新增数据失败，请联系数据库管理员"),
    DATA_UPDATE_ERROR(5002, "修改数据失败，请联系数据库管理员"),
    DATA_DELETE_ERROR(5003, "删除数据失败，请联系数据库管理员"),
    
    ;
    
    private final Integer code;
    private String msg;
    
    CodeMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public CodeMsgEnum setMsg(Object... msg) {
        this.msg = StrUtil.format(this.msg + StrUtil.repeat(" {}", msg.length), msg);
        return this;
    }
    
}
