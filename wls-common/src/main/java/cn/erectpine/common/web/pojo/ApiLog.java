package cn.erectpine.common.web.pojo;

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
     * 请求id
     */
    private String requestId;
    
    /**
     * 请求状态 200成功 500失败
     */
    private Integer status;
    
    /**
     * 接口耗时 单位毫秒
     */
    private long consumeTime;
    
    /**
     * 请求URL
     */
    private String url;
    
    /**
     * 请求方法
     */
    private String requestMethod;
    
    /**
     * 请求IP
     */
    private String ip;
    
    /**
     * 请求headers
     */
    private String headers;
    
    /**
     * 请求数据
     */
    private String requestData;
    
    /**
     * 处理请求方法
     */
    private String handleMethod;
    
    /**
     * 响应数据
     */
    private String responseData;
    
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
     * 异常堆栈信息
     */
    private String stacktrace;
    
    /**
     * 完整异常信息
     */
    private String error;
    
}
