package cn.erectpine.common.web.pojo;


import cn.erectpine.common.core.enums.CodeMsgEnum;
import cn.erectpine.common.core.jdkboost.map.PineStrObjMap;
import cn.erectpine.common.web.context.PineContext;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Optional;

/**
 * 响应模板
 *
 * @author wls
 * @date 2021/01/12
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 请求唯一ID
     */
    public String requestId;
    /**
     * 状态码
     */
    private String code;
    /**
     * 返回内容
     */
    private String msg;
    /**
     * 数据对象
     */
    private T data;
    /**
     * 扩展-map
     */
    private PineStrObjMap expandMap;
    /**
     * 扩展-对象
     */
    private Expand expand;
    
    
    /**
     * 初始化一个新创建的 Result 对象，使其表示一个空消息。
     */
    public Result() {
        this.requestId = PineContext.getRequestId();
    }
    
    /**
     * 添加扩展字段
     *
     * @param name name
     * @param val  val
     * @return {@link PineStrObjMap}
     */
    public Result<T> addField(String name, Object val) {
        expandMap = Optional.ofNullable(expandMap).orElseGet(PineStrObjMap::new).putItem(name, val);
        return this;
    }
    
    /**
     * 成功消息
     *
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> ok() {
        return ok(null);
    }
    
    /**
     * 成功消息
     *
     * @param data 数据
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> ok(T data) {
        return build(data, CodeMsgEnum.SUCCESS);
    }
    
    /**
     * 失败
     *
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> fail() {
        return fail(null);
    }
    
    /**
     * 失败
     *
     * @param data 数据
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> fail(T data) {
        return build(data, CodeMsgEnum.FAIL_UNKNOWN_ERROR);
    }
    
    /**
     * 失败
     *
     * @param codeMsgEnum codeMsgEnum
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> fail(CodeMsgEnum codeMsgEnum) {
        return build(null, codeMsgEnum);
    }
    
    /**
     * 构建返回体
     *
     * @param data        数据
     * @param codeMsgEnum codeMsgEnum
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> build(T data, CodeMsgEnum codeMsgEnum) {
        codeMsgEnum = Optional.ofNullable(codeMsgEnum).orElse(CodeMsgEnum.FAIL_UNKNOWN_ERROR);
        return new Result<T>().setCode(codeMsgEnum.getCode()).setMsg(codeMsgEnum.getMsg()).setData(data);
    }
    
}
