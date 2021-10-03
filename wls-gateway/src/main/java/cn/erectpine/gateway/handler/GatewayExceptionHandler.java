package cn.erectpine.gateway.handler;

import cn.erectpine.common.core.enums.CodeInfoEnum;
import cn.erectpine.common.core.exception.RequestHeaderException;
import cn.erectpine.gateway.util.WebFluxUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关统一异常处理
 *
 * @author wls
 * @since 2021/10/03 15:41:26
 */
@Order(Integer.MIN_VALUE + 1)
@Configuration
@Slf4j
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {
    
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable e) {
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(e);
        }
        if (e instanceof RequestHeaderException) {
            log.error("[网关异常统一处理] <请求头缺失> 请求路径:{} 异常信息:{}", exchange.getRequest().getPath(), e.getMessage(), e);
            return WebFluxUtil.webFluxResponseWriter(exchange, CodeInfoEnum.GATEWAY_HEADER_NOT_FOUND_ERROR.setInfo(e.getMessage()));
        }
        if (e instanceof NotFoundException) {
            log.error("[网关异常统一处理] <服务未找到> 请求路径:{} 异常信息:{}", exchange.getRequest().getPath(), e.getMessage(), e);
            return WebFluxUtil.webFluxResponseWriter(exchange, CodeInfoEnum.GATEWAY_SERVER_NOT_FOUND_ERROR);
        }
        if (e instanceof ResponseStatusException) {
            log.error("[网关异常统一处理] <响应状态码异常> 请求路径:{} 异常信息:{}", exchange.getRequest().getPath(), e.getMessage(), e);
            return WebFluxUtil.webFluxResponseWriter(exchange, CodeInfoEnum.UNKNOWN_GATEWAY_ERROR.setInfo(e.getMessage()));
        }
        
        log.error("[网关异常统一处理] <未知异常> 请求路径:{} 异常信息:{}", exchange.getRequest().getPath(), e.getMessage(), e);
        return WebFluxUtil.webFluxResponseWriter(exchange, CodeInfoEnum.UNKNOWN_GATEWAY_ERROR);
    }
    
}