package cn.erectpine.common.pojo;

import cn.erectpine.common.enums.CodeMsgEnum;
import cn.erectpine.common.generator.doc.WlsProperty;
import cn.hutool.json.JSON;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 接口日志
 *
 * @Author wls
 * @Date 2021/1/13 16:54
 */
@Accessors(chain = true)
@Data
@WlsProperty("接口日志")
public class ApiLog {
    
    /**
     * 请求id
     */
    @WlsProperty(value = "请求id", required = true)
    private String requestId;
    /**
     * 错误消息
     */
    @WlsProperty("错误消息")
    private String errorMsg;
    /**
     * 简单异常堆栈信息
     */
    @WlsProperty("简单异常堆栈信息")
    private JSON simpleStacktrace;
    /**
     * 请求数据
     */
    @WlsProperty("请求数据")
    private JSON requestData;
    /**
     * 请求headers
     */
    @WlsProperty("请求headers")
    private JSON headers;
    /**
     * 响应数据
     */
    @WlsProperty("响应数据")
    private JSON responseData;
    /**
     * 请求IP
     */
    @WlsProperty("请求IP")
    private String ip;
    /**
     * 请求URL
     */
    @WlsProperty("请求URL")
    private String url;
    /**
     * 请求状态 200成功 -500失败未处理
     */
    @WlsProperty(value = "请求状态 200成功 -500失败未处理", required = true)
    private CodeMsgEnum status;
    /**
     * 接口耗时 单位毫秒
     */
    @WlsProperty("接口耗时 单位毫秒")
    private long consumeTime;
    /**
     * 请求方式
     */
    @WlsProperty("请求方式")
    private String requestMethod;
    /**
     * 处理请求方法
     */
    @WlsProperty("处理请求方法")
    private String handleMethod;
    /**
     * 接口调用开始时间
     */
    @WlsProperty("接口调用开始时间")
    private LocalDateTime startTime;
    /**
     * 接口调用结束或异常终止时间
     */
    @WlsProperty("接口调用结束或异常终止时间")
    private LocalDateTime endTime;
    /**
     * 请求token
     */
    @WlsProperty("请求token")
    private String authorization;
    /**
     * 完整堆栈日志 暂时不记录
     */
    @WlsProperty("完整堆栈日志 暂时不记录")
    private JSON error;
    
}
