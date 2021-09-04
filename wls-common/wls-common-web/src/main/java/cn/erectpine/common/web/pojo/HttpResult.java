package cn.erectpine.common.web.pojo;


import cn.erectpine.common.core.enums.CodeMsgEnum;
import cn.erectpine.common.web.context.PineContext;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应模板
 *
 * @author wls
 * @date 2021/01/12
 */
@Data
@Accessors(chain = true)
public class HttpResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 请求唯一ID
     */
    public String requestId;
    /**
     * 返回内容
     */
    public String msg;
    /**
     * 数据对象
     */
    public T data;
    /**
     * 状态码
     */
    private String code;
    
    
    /**
     * 初始化一个新创建的 ResponseTemplate 对象，使其表示一个空消息。
     */
    public HttpResult() {
        this.requestId = PineContext.getRequestId();
    }
    
    public static <T> HttpResult<T> success() {
        return new HttpResult<T>().setCode(CodeMsgEnum.SUCCESS.getCode()).setMsg(CodeMsgEnum.SUCCESS.getMsg());
    }
    
    public static <T> HttpResult<T> success(T data) {
        return new HttpResult<T>().setCode(CodeMsgEnum.SUCCESS.getCode()).setMsg(CodeMsgEnum.SUCCESS.getMsg()).setData(data);
    }
    
    public static <T> HttpResult<T> success(T data, CodeMsgEnum codeMsgEnum) {
        return new HttpResult<T>().setCode(codeMsgEnum.getCode()).setMsg(codeMsgEnum.getMsg()).setData(data);
    }
    
    public static <T> HttpResult<T> error(CodeMsgEnum codeMsgEnum) {
        return new HttpResult<T>().setCode(codeMsgEnum.getCode()).setMsg(codeMsgEnum.getMsg());
    }
    
    public static <T> HttpResult<T> error(CodeMsgEnum codeMsgEnum, T data) {
        return new HttpResult<T>().setCode(codeMsgEnum.getCode()).setMsg(codeMsgEnum.getMsg()).setData(data);
    }
    
}
