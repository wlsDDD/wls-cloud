import cn.hutool.crypto.digest.DigestUtil;
import org.junit.jupiter.api.Test;

/**
 * 测试类
 *
 * @author wls
 * @since 2021/9/17 13:05
 */
public class Tests {
    
    @Test
    public void test02() {
    
    }
    
    @Test
    public void test01() {
        String md5Hex1 = DigestUtil.md5Hex("testStr");
        String sha256Hex = DigestUtil.sha256Hex("testStr");
        String md5Hex16 = DigestUtil.md5Hex16("testStr");
    }
    
}
