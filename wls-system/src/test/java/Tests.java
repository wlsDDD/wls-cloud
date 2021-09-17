import cn.erectpine.common.core.pojo.ApiLog;
import cn.erectpine.common.core.util.pine.PineUtil;
import cn.erectpine.common.gencode.MysqlGenerator;
import cn.erectpine.common.gencode.md.MdGenerator;
import cn.erectpine.system.project.entity.User;
import cn.hutool.core.util.IdUtil;
import cn.hutool.system.SystemUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author wls
 * @Date 2021/4/4 14:39
 */
public class Tests {
    
    String str = "varchar(255)";
    boolean a;
    
    @Test
    public void test11() {
        long l = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println("l = " + l);
    }
    
    @Test
    public void test10() {
        long totalMemory = SystemUtil.getMaxMemory() / 1024 / 1024;
        System.out.println("totalMemory = " + totalMemory);
    }
    
    @Test
    public void test09() {
        String s = IdUtil.simpleUUID();
        System.out.println("s = " + s);
    }
    
    
    @Test
    public void test07() {
        MdGenerator.genMd(User.class);
    }
    
    @Test
    public void test06() {
        ApiLog apiLog = new ApiLog();
    }
    
    @Test
    public void genCode() {
        MysqlGenerator.packagePath = "cn.erectpine.system.project";
        MysqlGenerator.tablePrefix = "sys_";
        MysqlGenerator.generatorCode("sys_config", "sys_user");
    }
    
    @Test
    public void test05() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2, 4, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        threadPool.execute(() -> System.out.println("子线程"));
        
    }
    
    @Test
    public void test04() {
        System.out.println("a = " + a);
        System.out.println("(int)a = " + a + 1);
    }
    
    @Test
    public void test03() {
        Integer numeric = PineUtil.getNumeric(str);
        System.out.println("numeric = " + numeric);
    }
    
    @Test
    public void test02() {
        int integer = Integer.parseInt(str);
        System.out.println("integer = " + integer);
    }
    
}
