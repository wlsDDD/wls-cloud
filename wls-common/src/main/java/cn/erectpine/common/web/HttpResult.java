package cn.erectpine.common.web;


import cn.erectpine.common.constant.HttpStatus;
import cn.erectpine.common.context.WlsContext;
import cn.erectpine.common.enums.CodeMsgEnum;
import cn.erectpine.common.pojo.ApiLog;
import cn.erectpine.common.util.LamUtil;
import cn.erectpine.common.web.exception.BaseRunTimeException;

import java.util.HashMap;

/**
 * 响应模板
 *
 * @author wls
 * @date 2021/01/12
 */
public class HttpResult extends HashMap<String, Object> {
    
    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";
    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";
    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";
    /**
     * 请求唯一ID
     */
    public static final String REQUEST_ID_TAG = LamUtil.getFieldName(ApiLog::getRequestId);
    /**
     * 请求操作成功提示语
     */
    public static final String SUCCESS_MSG = CodeMsgEnum.SUCCESS.getMsg();
    /**
     * 请求操作失败提示语
     */
    public static final String ERROR_MSG = "操作失败";
    private static final long serialVersionUID = 1L;
    
    
    /**
     * 初始化一个新创建的 ResponseTemplate 对象，使其表示一个空消息。
     */
    public HttpResult() {
        super.put(REQUEST_ID_TAG, WlsContext.getRequestId());
    }
    
    /**
     * 初始化一个新创建的 ResponseTemplate 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public HttpResult(int code, String msg) {
        super.put(REQUEST_ID_TAG, WlsContext.getRequestId());
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }
    
    /**
     * 初始化一个新创建的 ResponseTemplate 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public HttpResult(int code, String msg, Object data) {
        super.put(REQUEST_ID_TAG, WlsContext.getRequestId());
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        super.put(DATA_TAG, null == data ? "" : data);
    }
    
    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static HttpResult success() {
        return HttpResult.success(SUCCESS_MSG);
    }
    
    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static HttpResult success(Object data) {
        return HttpResult.success(SUCCESS_MSG, data);
    }
    
    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static HttpResult success(String msg) {
        return HttpResult.success(msg, null);
    }
    
    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static HttpResult success(String msg, Object data) {
        return new HttpResult(HttpStatus.SUCCESS, msg, data);
    }
    
    /**
     * 错误
     * 返回错误消息
     *
     * @return {@link HttpResult}
     */
    public static HttpResult error() {
        return HttpResult.error(ERROR_MSG);
    }
    
    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static HttpResult error(String msg) {
        return HttpResult.error(msg, null);
    }
    
    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static HttpResult error(String msg, Object data) {
        return new HttpResult(HttpStatus.ERROR, msg, data);
    }
    
    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static HttpResult error(int code, String msg) {
        return new HttpResult(code, msg, null);
    }
    
    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static HttpResult error(int code, String msg, Object data) {
        return new HttpResult(code, msg, data);
    }
    
    /**
     * 返回错误消息
     *
     * @param codeMsgEnum {@link CodeMsgEnum}
     * @return 警告消息
     */
    public static HttpResult error(CodeMsgEnum codeMsgEnum) {
        WlsContext.getApiLog().setStatus(codeMsgEnum);
        return error(codeMsgEnum.getCode(), codeMsgEnum.getMsg());
    }
    
    /**
     * 返回错误消息
     *
     * @param e {@link BaseRunTimeException}
     * @return 警告消息
     */
    public static HttpResult error(BaseRunTimeException e) {
        return error(e.getCode(), e.getMessage());
    }
    
    /**
     * 重写put方法,方便链式调用
     *
     * @param key   key
     * @param value object
     * @return {@link HttpResult}
     */
    @Override
    public HttpResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
    
}
