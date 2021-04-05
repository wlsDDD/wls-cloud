package cn.erectpine.common.enums;


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
    SUCCESS(200, "success"),
    /**
     * 统一验证参数错误
     */
    ARG_VERIFY_ERROR(-1, "统一验证参数不合法, 请检查参数后重试! 以下信息仅供参考 \r\n"),
    /**
     * 业务类异常，提示信息由抛异常时定义
     */
    BUSINESS_ERROR(6001, ""),
    DATA_INSERT_ERROR(5001, "新增数据失败，请联系数据库管理员"),
    DATA_UPDATE_ERROR(5002, "修改数据失败，请联系数据库管理员"),
    DATA_DELETE_ERROR(5003, "删除数据失败，请联系数据库管理员"),
    /**
     * 项目启动时根据环境设置提示信息
     * 最终兜底方案
     * 其他状态码应避开此状态码
     */
    UNKNOWN_ERROR(500, ""),
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
