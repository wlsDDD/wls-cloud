package plus.wls.common.core.pojo;

import cn.hutool.json.JSON;
import lombok.Data;
import lombok.experimental.Accessors;
import plus.wls.common.core.enums.CodeInfoEnum;

import java.time.LocalDateTime;

/**
 * 接口日志
 *
 * @author wls
 * @since 2021/1/13 16:54
 */
@Data
@Accessors(chain = true)
public class ApiLog {
    
    /**
     * 请求id
     */
    private String requestId;
    /**
     * 错误消息
     */
    private String errorMsg;
    /**
     * 简单异常堆栈信息
     */
    private JSON simpleStacktrace;
    /**
     * 请求数据
     */
    private JSON requestData;
    /**
     * 请求headers
     */
    private JSON headers;
    /**
     * 响应数据
     */
    private JSON responseData;
    /**
     * 请求IP
     */
    private String ip;
    /**
     * 请求URL
     */
    private String url;
    /**
     * 请求状态 200成功
     * 500失败未处理
     */
    private CodeInfoEnum status;
    /**
     * 接口耗时 单位毫秒
     */
    private long consumeTime;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 处理请求方法
     */
    private String handleMethod;
    /**
     * 接口调用开始时间
     */
    private LocalDateTime startTime;
    /**
     * 接口调用结束或异常终止时间
     */
    private LocalDateTime endTime;
    /**
     * 请求token
     */
    private String authorization;
    /**
     * 完整堆栈日志 暂时不记录
     */
    private JSON error;
    
}
