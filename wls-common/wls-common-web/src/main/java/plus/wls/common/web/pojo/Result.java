package plus.wls.common.web.pojo;


import lombok.Data;
import lombok.experimental.Accessors;
import plus.wls.common.core.constant.SuppressWarningConstants;
import plus.wls.common.core.enums.CodeInfoEnum;
import plus.wls.common.core.util.TreeUtil;
import plus.wls.common.web.context.HttpContext;
import plus.wls.common.web.util.PageUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 响应http请求模板
 *
 * @author wls
 * @since 2021/01/12
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 请求唯一ID
     */
    private String requestId;
    
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回内容
     */
    private String info;
    /**
     * 数据对象
     */
    private T data;
    
    /**
     * 返回结果扩展
     */
    private ResultExtra extra;
    
    
    /**
     * 初始化一个新创建的 Result 对象，使其表示一个空消息。
     */
    public Result() {
        this.requestId = HttpContext.getRequestId();
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
     *
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> ok(T data) {
        return build(data, CodeInfoEnum.SUCCESS);
    }
    
    /**
     * 构建返回体
     *
     * @param data         数据
     * @param codeInfoEnum codeMsgEnum
     *
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> build(T data, CodeInfoEnum codeInfoEnum) {
        codeInfoEnum = Optional.ofNullable(codeInfoEnum).orElse(CodeInfoEnum.UNKNOWN_ERROR);
        return new Result<T>().setCode(codeInfoEnum.getCode()).setInfo(codeInfoEnum.getInfo()).setData(data);
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
     * @param codeInfoEnum codeMsgEnum
     *
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> fail(CodeInfoEnum codeInfoEnum) {
        return build(null, codeInfoEnum);
    }
    
    /**
     * 失败
     *
     * @param data 数据
     *
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> fail(T data) {
        return build(data, CodeInfoEnum.UNKNOWN_ERROR);
    }
    
    /**
     * 列表->树
     *
     * @return {@link Result}<{@link T}>
     */
    @SuppressWarnings({SuppressWarningConstants.UNCHECKED})
    public Result<T> tree() {
        if (data instanceof List) {
            extra = Optional.ofNullable(extra).orElseGet(ResultExtra::new).setTree(TreeUtil.toTree((List<T>) data));
        }
        return this;
    }
    
    /**
     * 列表->分页
     *
     * @return {@link Result}<{@link T}>
     */
    @SuppressWarnings({SuppressWarningConstants.UNCHECKED})
    public Result<T> page() {
        if (data instanceof List) {
            extra = Optional.ofNullable(extra).orElseGet(ResultExtra::new).setPage(PageUtil.page((List<T>) data));
        }
        return this;
    }
    
    /**
     * 参数错误提示信息
     *
     * @param paramErrors 参数错误
     *
     * @return {@link Result}<{@link T}>
     */
    public Result<T> paramErrors(Map<String, String> paramErrors) {
        extra = Optional.ofNullable(extra).orElseGet(ResultExtra::new).setParamErrors(paramErrors);
        return this;
    }
    
}
