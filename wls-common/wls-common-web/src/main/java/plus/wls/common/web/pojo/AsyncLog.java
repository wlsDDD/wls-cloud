package plus.wls.common.web.pojo;

import cn.hutool.json.JSONArray;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 异步任务执行日志
 *
 * @author wls
 * @date 2022-06-07 18:45:28
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class AsyncLog {
    
    /**
     * 请求id
     */
    private String requestId;
    /**
     * 请求token
     */
    private String authorization;
    /**
     * 请求数据
     */
    private JSONArray param;
    /**
     * 任务执行状态 0成功 1失败
     */
    private Integer status;
    
    /**
     * 执行任务方法名称
     */
    private String methodName;
    /**
     * 接口调用开始时间
     */
    private LocalDateTime startTime;
    /**
     * 接口调用结束或异常终止时间
     */
    private LocalDateTime endTime;
    /**
     * 任务执行耗时 单位毫秒
     */
    private long consumeTime;
    
    /**
     * 错误消息
     */
    private String errorMsg;
    private AsyncLog asyncLog;
    private List<AsyncLog> asyncLogs;
    /**
     * 简单异常堆栈信息
     */
    private JSONArray simpleStacktrace;
    /**
     * 完整堆栈日志 暂时不记录
     */
    private JSONArray stacktrace;
    
}
