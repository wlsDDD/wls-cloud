package cn.erectpine.gateway.util;

import cn.erectpine.common.core.enums.CodeInfoEnum;
import cn.erectpine.common.core.jdkboost.map.PineStrObjMap;
import cn.erectpine.common.core.pojo.ApiLog;
import cn.erectpine.common.core.util.pine.LamUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网络流量工具类
 *
 * @author wls
 * @since 2021/10/03 12:46:47
 */
public class WebFluxUtil {
    
    /**
     * web流量响应作家
     * 写入响应信息
     *
     * @param exchange     交换
     * @param codeInfoEnum 枚举代码信息
     * @return {@link Mono}<{@link Void}>
     */
    public static Mono<Void> webFluxResponseWriter(ServerWebExchange exchange, CodeInfoEnum codeInfoEnum) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer dataBuffer = response.bufferFactory()
                                        .wrap(JSONObject.toJSONString(new PineStrObjMap()
                                                .putItem(ApiLog::getRequestId, request.getHeaders().toSingleValueMap().get(LamUtil.getFieldName(ApiLog::getRequestId)))
                                                .putItem(CodeInfoEnum::getCode, codeInfoEnum.getCode())
                                                .putItem(CodeInfoEnum::getInfo, codeInfoEnum.getInfo())).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
    
    
}
