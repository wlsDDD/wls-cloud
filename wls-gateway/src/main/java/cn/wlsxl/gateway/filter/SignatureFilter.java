package cn.wlsxl.gateway.filter;

import cn.hutool.crypto.digest.DigestUtil;
import cn.wlsxl.common.core.enums.CodeInfoEnum;
import cn.wlsxl.common.core.exception.IllegalRequestException;
import cn.wlsxl.common.core.pojo.Signature;
import cn.wlsxl.common.core.util.LamUtil;
import cn.wlsxl.common.core.util.Pines;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

/**
 * 签名验证过滤器
 *
 * @author wls
 * @since 2021/10/1 9:12
 */
@Slf4j
@Component
public class SignatureFilter implements GlobalFilter, Ordered {
    
    @Autowired
    Signature signature;
    
    /**
     * 1.生成 requestId 此唯一id将会传递到每一个服务
     * 2.验证签名 以下参数参与签名 排序方式为字典排序
     * 2.1.appKey
     * 2.2.appSecret
     * 2.3.timestamp
     * 2.4.version
     * 2.5.url参数...
     * 3.拼接参数 拼接方式为 key=value;key=value;...
     * 4.拼接完后用 {@link URLEncoder} 中的 encode 方法进行编码
     * 5.编码完成后用sha256计算摘要
     * 6.签名字符串生成结束
     * 7.比对签名串 一致签名通过
     *
     * @return {@link Mono}<{@link Void}>
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 未开启签名验证则直接进入下一个调用链
        if (Boolean.FALSE.equals(signature.getEnable())) {
            return chain.filter(exchange);
        }
        ServerHttpRequest request = exchange.getRequest();
        Map<String, String> headerMap = request.getHeaders().toSingleValueMap();
        // 获取签名参数
        TreeMap<String, String> signatureMap = new TreeMap<>();
        String appKey = LamUtil.getFieldName(Signature::getAppKey);
        String appSecret = LamUtil.getFieldName(Signature::getAppSecret);
        String timestamp = LamUtil.getFieldName(Signature::getTimestamp);
        String version = LamUtil.getFieldName(Signature::getVersion);
        String randomStr = LamUtil.getFieldName(Signature::getRandomStr);
        String signatureKey = LamUtil.getFieldName(Signature::getSign);
        signatureMap.put(appKey, Pines.getOrException(headerMap, appKey));
        signatureMap.put(appSecret, Pines.getOrException(headerMap, appSecret));
        signatureMap.put(timestamp, Pines.getOrException(headerMap, timestamp));
        signatureMap.put(version, Pines.getOrException(headerMap, version));
        signatureMap.put(randomStr, Pines.getOrException(headerMap, randomStr));
        // 加入URL参数
        request.getQueryParams().forEach((k, v) -> signatureMap.put(k, v.toString().replaceAll("[\\[\\]]", "")));
        // 拼接参数
        StringBuilder signatureBuilder = new StringBuilder();
        signatureMap.forEach((s, s2) -> signatureBuilder.append(s).append("=").append(s2).append(";"));
        String signatureStr;
        // 放弃URL编码
        try {
            signatureStr = URLEncoder.encode(signatureBuilder.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            signatureStr = signatureBuilder.toString();
            log.error("字符转码失败 URLEncoder 降级使用原编码", e);
        }
        // sha256计算摘要
        signatureStr = DigestUtil.sha256Hex(signatureStr);
        // 验证签名 失败直接返回
        if (!signatureStr.equals(Pines.getOrException(headerMap, signatureKey))) {
            throw new IllegalRequestException(CodeInfoEnum.SIGNATURE_VERIFY_ERROR);
        }
        log.debug("[签名验证成功] <{}>", signatureStr);
        // 成功 继续向后调用
        return chain.filter(exchange);
    }
    
    @Override
    public int getOrder() {
        return -1000;
    }
    
}
