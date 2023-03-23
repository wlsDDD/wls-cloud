package plus.wls.gateway.filter;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import plus.wls.common.core.pojo.ApiLog;
import plus.wls.common.core.util.LamUtil;
import reactor.core.publisher.Mono;

/**
 * 生成requestId
 *
 * @author wls
 * @since 2021/10/1 9:12
 */
@Slf4j
@Component
public class RequestIdFilter implements GlobalFilter, Ordered {
    
    /**
     * 生成requestId
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestId = IdUtil.simpleUUID();
        ServerHttpRequest request = exchange.getRequest().mutate().header(LamUtil.fieldName(ApiLog::getRequestId), requestId).build();
        log.debug("[requestId生成] <{}>", requestId);
        return chain.filter(exchange.mutate().request(request).build());
    }
    
    @Override
    public int getOrder() {
        return -5000;
    }
    
}
