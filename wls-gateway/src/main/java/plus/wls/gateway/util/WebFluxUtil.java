package plus.wls.gateway.util;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import plus.wls.common.core.enums.CodeInfoEnum;
import plus.wls.common.core.pojo.ApiLog;
import plus.wls.common.core.util.LamUtil;
import reactor.core.publisher.Mono;

import java.util.Map;

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
     *
     * @return {@link Mono}<{@link Void}>
     */
    public static Mono<Void> webFluxResponseWriter(ServerWebExchange exchange, CodeInfoEnum codeInfoEnum) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        Map<String, Object> map = MapUtil.of(
                new Pair<>(LamUtil.fieldName(CodeInfoEnum::getCode), codeInfoEnum.getCode()),
                new Pair<>(LamUtil.fieldName(CodeInfoEnum::getInfo), codeInfoEnum.getInfo()),
                new Pair<>(LamUtil.fieldName(ApiLog::getRequestId), request.getHeaders().get(LamUtil.fieldName(ApiLog::getRequestId)))
        );
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(map).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
    
    
}
