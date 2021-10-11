package cn.erectpine.common.web.pojo;


import cn.erectpine.common.core.constant.SuppressWarningConstants;
import cn.erectpine.common.core.enums.CodeInfoEnum;
import cn.erectpine.common.core.pojo.Page;
import cn.erectpine.common.core.util.pine.TreeUtil;
import cn.erectpine.common.web.context.HttpContext;
import cn.erectpine.common.web.util.PageUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collection;
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
    public String requestId;
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
     * 分页列表
     */
    private Page<T> page;
    /**
     * 树列表
     */
    private Collection<?> tree;
    /**
     * 参数错误消息
     */
    private Map<String, String> paramErrors;
    /**
     * 扩展-对象
     */
    private ResultExtra extra;
    
    
    /**
     * 初始化一个新创建的 Result 对象，使其表示一个空消息。
     */
    public Result() {
        this.requestId = HttpContext.getRequestId();
    }
    
    /**
     * 列表->树
     *
     * @return {@link Result}<{@link T}>
     */
    @SuppressWarnings({SuppressWarningConstants.UNCHECKED})
    public Result<T> tree() {
        if (data instanceof List) {
            this.tree = TreeUtil.toTree((List<T>) data);
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
            this.page = PageUtil.page((List<T>) data);
        }
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
        return build(data, CodeInfoEnum.SUCCESS);
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
        return build(data, CodeInfoEnum.UNKNOWN_ERROR);
    }
    
    /**
     * 失败
     *
     * @param codeInfoEnum codeMsgEnum
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> fail(CodeInfoEnum codeInfoEnum) {
        return build(null, codeInfoEnum);
    }
    
    /**
     * 构建返回体
     *
     * @param data         数据
     * @param codeInfoEnum codeMsgEnum
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> build(T data, CodeInfoEnum codeInfoEnum) {
        codeInfoEnum = Optional.ofNullable(codeInfoEnum).orElse(CodeInfoEnum.UNKNOWN_ERROR);
        return new Result<T>().setCode(codeInfoEnum.getCode()).setInfo(codeInfoEnum.getInfo()).setData(data);
    }
    
}
