package plus.wls.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 日志过滤器
 *
 * @author wls
 * @since 2021/10/03 21:30:19
 */
@Slf4j
@Component
public class LogFilter implements GlobalFilter, Ordered {
    
    /**
     * 记录日志
     * TODO
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();

//        response.bufferFactory().allocateBuffer().
        return chain.filter(exchange);
    }
    
    @Override
    public int getOrder() {
        return -4000;
    }
    
}
