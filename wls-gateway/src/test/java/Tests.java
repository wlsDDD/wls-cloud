import cn.hutool.crypto.digest.DigestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

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
    
    
}
