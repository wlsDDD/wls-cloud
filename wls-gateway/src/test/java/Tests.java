import cn.erectpine.common.core.context.HttpContext;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author wls
 * @since 2021/9/30 12:07
 */
@Slf4j
public class Tests {
    
    String appKey = "wls-cloud-app-key";
    String appSecret = "wls-cloud-app-secret";
    String timestamp = System.currentTimeMillis() + "";
    String version = "1.0.0";
    
    @Test
    public void test01() throws Exception {
        Map<String, String> map = new TreeMap<>();
        map.put("apKey", appKey);
        map.put("appSecret", appSecret);
        map.put("timestamp", timestamp);
        map.put("version", version);
        map.put("1", null);
        map.put("2", null);
        StringBuilder str = new StringBuilder();
        map.forEach((s, s2) -> str.append(s).append("=").append(s2).append(";"));
        String encode = URLEncoder.encode(str.toString(), "utf-8");
        String sha256Hex = DigestUtil.sha256Hex(encode);
        int length = sha256Hex.length();
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
    public static TreeMap<String, String> getSignParamMap() {
        HttpServletRequest request = HttpContext.getContext().getRequest();
        Map<String, String> headerMap = ServletUtil.getHeaderMap(request);
        Map<String, String> paramMap = ServletUtil.getParamMap(request);
        TreeMap<String, String> map = new TreeMap<>();
        map.put("apKey", headerMap.get("apKey"));
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
    public static String getSignStr() {
        TreeMap<String, String> map = getSignParamMap();
        StringBuilder str = new StringBuilder();
        map.forEach((s, s2) -> str.append(s).append("=").append(s2).append(";"));
        String encode;
        try {
            encode = URLEncoder.encode(str.toString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.warn("签名字符串转URL编码失败, 降级直接使用字符串验证", e);
            encode = str.toString();
        }
        return DigestUtil.sha256Hex(encode);
    }
    
    
}
