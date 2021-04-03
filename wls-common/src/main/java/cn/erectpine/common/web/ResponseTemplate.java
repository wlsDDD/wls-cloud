package cn.erectpine.common.web;


import cn.erectpine.common.constant.HttpStatus;
import cn.erectpine.common.enums.CodeMsgEnum;
import cn.erectpine.common.enums.SystemEnum;
import cn.erectpine.common.util.ServletUtil;
import cn.erectpine.common.web.exception.BaseRunTimeException;
import cn.hutool.core.bean.BeanUtil;

import java.util.HashMap;

/**
 * 响应模板
 *
 * @author wls
 * @date 2021/01/12
 */
public class ResponseTemplate extends HashMap<String, Object> {
    
    private static final long serialVersionUID = 1L;
    
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
    public static final String REQUEST_ID = SystemEnum.requestId.name();
    
    /**
     * 请求操作成功提示语
     */
    public static final String SUCCESS_MSG = "操作成功";
    /**
     * 请求操作失败提示语
     */
    public static final String ERROR_MSG = "操作失败";
    
    
    /**
     * 初始化一个新创建的 ResponseTemplate 对象，使其表示一个空消息。
     */
    public ResponseTemplate() {
        super.put(REQUEST_ID, ServletUtil.getAttribute(SystemEnum.requestId.name()));
    }
    
    /**
     * 初始化一个新创建的 ResponseTemplate 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public ResponseTemplate(int code, String msg) {
        super.put(REQUEST_ID, ServletUtil.getAttribute(SystemEnum.requestId.name()));
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
    public ResponseTemplate(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        super.put(REQUEST_ID, ServletUtil.getAttribute(SystemEnum.requestId.name()));
        if (BeanUtil.isNotEmpty(data)) {
            super.put(DATA_TAG, data);
        }
        
    }
    
    
    /**
     * 重写put方法,方便链式调用
     *
     * @param key   key
     * @param value object
     * @return {@link ResponseTemplate}
     */
    @Override
    public ResponseTemplate put(String key, Object value) {
        super.put(key, value);
        return this;
    }
    
    
    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ResponseTemplate success() {
        return ResponseTemplate.success(SUCCESS_MSG);
    }
    
    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ResponseTemplate success(Object data) {
        return ResponseTemplate.success(SUCCESS_MSG, data);
    }
    
    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ResponseTemplate success(String msg) {
        return ResponseTemplate.success(msg, null);
    }
    
    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResponseTemplate success(String msg, Object data) {
        return new ResponseTemplate(HttpStatus.SUCCESS, msg, data);
    }
    
    
    /**
     * 错误
     * 返回错误消息
     *
     * @return {@link ResponseTemplate}
     */
    public static ResponseTemplate error() {
        return ResponseTemplate.error(ERROR_MSG);
    }
    
    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResponseTemplate error(String msg) {
        return ResponseTemplate.error(msg, null);
    }
    
    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResponseTemplate error(String msg, Object data) {
        return new ResponseTemplate(HttpStatus.ERROR, msg, data);
    }
    
    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static ResponseTemplate error(int code, String msg) {
        return new ResponseTemplate(code, msg, null);
    }
    
    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResponseTemplate error(int code, String msg, Object data) {
        return new ResponseTemplate(code, msg, data);
    }
    
    /**
     * 返回错误消息
     *
     * @param codeMsgEnum {@link CodeMsgEnum}
     * @return 警告消息
     */
    public static ResponseTemplate error(CodeMsgEnum codeMsgEnum) {
        return error(codeMsgEnum.getCode(), codeMsgEnum.getMsg());
    }
    
    /**
     * 返回错误消息
     *
     * @param e {@link BaseRunTimeException}
     * @return 警告消息
     */
    public static ResponseTemplate error(BaseRunTimeException e) {
        return error(e.getCode(), e.getMessage());
    }
    
}
