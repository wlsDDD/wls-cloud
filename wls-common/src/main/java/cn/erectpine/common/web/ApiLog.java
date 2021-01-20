package cn.erectpine.common.web;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 接口日志实体
 *
 * @Author wls
 * @Date 2021/1/13 16:54
 */
@Accessors(chain = true)
@Data
public class ApiLog {
    
    /**
     * 请求状态 200成功 500失败
     */
    private Integer       status;
    /**
     * 接口耗时 单位毫秒
     */
    private long          consumeTime;
    /**
     * 请求数据
     */
    private String        method;
    /**
     * 请求数据
     */
    private String        requestData;
    /**
     * 响应数据
     */
    private String        responseData;
    /**
     * 请求URL
     */
    private String        url;
    /**
     * 请求IP
     */
    private String        ip;
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
    private String        authorization;
    /**
     * 错误信息
     */
    private String        errorMessage;
    /**
     * 错误堆栈信息
     */
    private String        stacktrace;
    
}
