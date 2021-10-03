package cn.erectpine.gateway.util;

import cn.erectpine.common.core.enums.CodeInfoEnum;
import cn.erectpine.common.core.jdkboost.map.PineStrObjMap;
import cn.erectpine.common.core.pojo.ApiLog;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

/**
 * 网络流量工具类
 *
 * @author wls
 * @since 2021/10/03 12:46:47
 */
public class WebFluxUtil {
    
    /**
     * 写入响应信息
     *
     * @param response 响应
     * @param value    价值
     * @return {@link Mono}<{@link Void}>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, Object value) {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(value).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
    
    /**
     * 获取响应结果
     *
     * @param code 代码
     * @param info 信息
     * @return {@link PineStrObjMap}
     */
    public static PineStrObjMap getResult(String requestId, String code, String info) {
        return new PineStrObjMap().putItem(ApiLog::getRequestId, requestId).putItem(CodeInfoEnum::getCode, code).putItem(CodeInfoEnum::getInfo, info);
    }
    
}
