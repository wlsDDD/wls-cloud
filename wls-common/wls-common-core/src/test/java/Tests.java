import cn.hutool.core.convert.Convert;
import cn.hutool.system.SystemUtil;
import cn.wlsxl.common.core.util.Pines;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author wls
 * @since 2021/8/21 21:28
 */
public class Tests {
    String sql = "SELECT user_id,dept_id,user_name,nick_name,user_type,email,phonenumber,sex,avatar,password,status,del_flag,login_ip,login_date,remark,create_time,update_time,create_by,update_by FROM sys_user LIMIT ?,?\n";
    String param = "2(Long), 2(Long)";
    
    @Test
    public void test02() {
        String str = "askd465465kajsdlk";
        Integer toInt = Convert.toInt(str);
        Integer numeric = Pines.toInt(str);
    }
    
    @Test
    public void test01() {
        System.out.println("SystemUtil.getTotalThreadCount() = " + SystemUtil.getTotalThreadCount());
        SystemUtil.dumpSystemInfo();
    }
    
    @Test
    void test03() {
        TimeUnit unit = TimeUnit.SECONDS;
        long timeout = 30;
        long l = unit.toSeconds(timeout);
        long l2 = TimeUnit.HOURS.toSeconds(timeout);
        long l3 = TimeUnit.MINUTES.toSeconds(timeout);
    }
    
    
}
