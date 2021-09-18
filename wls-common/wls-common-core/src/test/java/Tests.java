import cn.hutool.system.SystemUtil;
import org.junit.jupiter.api.Test;

/**
 * @author wls
 * @since 2021/8/21 21:28
 */
public class Tests {
    
    @Test
    public void test01() {
        System.out.println("SystemUtil.getTotalThreadCount() = " + SystemUtil.getTotalThreadCount());
        SystemUtil.dumpSystemInfo();
    }
    
    
}
