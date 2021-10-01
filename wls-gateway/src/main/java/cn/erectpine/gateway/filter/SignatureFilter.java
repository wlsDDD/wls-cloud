package cn.erectpine.gateway.filter;

import cn.erectpine.common.core.context.HttpContext;
import cn.erectpine.common.core.pojo.Signature;
import cn.erectpine.common.core.util.pine.LamUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

/**
 * 验证签名过滤器
 *
 * @author wls
 * @since 2021/10/1 9:12
 */
@Slf4j
@Component
public class SignatureFilter implements GlobalFilter, Ordered {
    
    @Autowired Signature signature;
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 未开启签名验证则直接进入下一个调用链
        if (!signature.getSignatureFlag()) {
            return chain.filter(exchange);
        }
        
        return null;
    }
    
    @Override
    public int getOrder() {
        return -1000;
    }
    
    /**
     * 获取签名参数集合
     * 参与签名排序方式为字典排序
     * 以下参数参与签名
     * 1.apKey
     * 2.appSecret
     * 3.timestamp
     * 4.version
     * 5.url参数...
     *
     * @return {@link TreeMap}
     */
    private static TreeMap<String, String> getSignParamMap() {
        HttpServletRequest request = HttpContext.getContext().getRequest();
        Map<String, String> headerMap = ServletUtil.getHeaderMap(request);
        Map<String, String> paramMap = ServletUtil.getParamMap(request);
        TreeMap<String, String> map = new TreeMap<>();
        String appKey = LamUtil.getFieldName(Signature::getAppKey);
        String appSecret = LamUtil.getFieldName(Signature::getAppSecret);
        String timestamp = LamUtil.getFieldName(Signature::getTimestamp);
        String version = LamUtil.getFieldName(Signature::getVersion);
        String randomStr = LamUtil.getFieldName(Signature::getRandomStr);
        map.put(appKey, headerMap.get(appKey));
        map.put(appSecret, headerMap.get(appSecret));
        map.put(timestamp, headerMap.get(timestamp));
        map.put(version, headerMap.get(version));
        map.put(randomStr, headerMap.get(randomStr));
        map.putAll(paramMap);
        return map;
    }
    
    /**
     * 获取签名字符串
     * 1.拼接参数 拼接方式为 key=value;key=value;...
     * 2.拼接完后用 {@link URLEncoder} 中的 encode 方法进行编码
     * 3.编码完成后用sha256计算摘要
     * 4.返回摘要（即为最终签名结果）
     *
     * @return {@link String}
     */
    private static String getSignatureStr() {
        TreeMap<String, String> map = getSignParamMap();
        StringBuilder str = new StringBuilder();
        map.forEach((s, s2) -> str.append(s).append("=").append(s2).append(";"));
        String encode;
        try {
            encode = URLEncoder.encode(str.toString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.warn("签名字符串转URL编码失败, 降级直接返回字符串", e);
            encode = str.toString();
        }
        return DigestUtil.sha256Hex(encode);
    }
    
}
