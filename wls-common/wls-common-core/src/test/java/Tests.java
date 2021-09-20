import cn.hutool.system.SystemUtil;
import org.junit.jupiter.api.Test;

/**
 * @author wls
 * @since 2021/8/21 21:28
 */
public class Tests {
    String sql = "SELECT user_id,dept_id,user_name,nick_name,user_type,email,phonenumber,sex,avatar,password,status,del_flag,login_ip,login_date,remark,create_time,update_time,create_by,update_by FROM sys_user LIMIT ?,?\n";
    String param = "2(Long), 2(Long)";
    
    @Test
    public void test02() {
    
    }
    
    @Test
    public void test01() {
        System.out.println("SystemUtil.getTotalThreadCount() = " + SystemUtil.getTotalThreadCount());
        SystemUtil.dumpSystemInfo();
    }
    
    
}
