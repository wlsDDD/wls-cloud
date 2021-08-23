import cn.erectpine.common.core.util.pine.PineUtil;
import cn.erectpine.common.gencode.MysqlGenerator;
import cn.erectpine.common.gencode.md.MdGenerator;
import cn.erectpine.common.web.pojo.ApiLog;
import cn.erectpine.system.project.entity.User;
import cn.hutool.core.util.IdUtil;
import cn.hutool.system.SystemUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
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
    public void test08() {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir") + "/src/test/java");
        gc.setAuthor("wls");
        gc.setOpen(false);
        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);
        gc.setDateType(DateType.TIME_PACK);
        gc.setIdType(IdType.AUTO);
        gc.setFileOverride(false);
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://rm-uf66903d9ae3a6k15fo.mysql.rds.aliyuncs.com:3306/dev_persontax?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&allowPublicKeyRetrieval=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setDbType(DbType.MYSQL);
        dsc.setUsername("ming");
        dsc.setPassword("Ming2020--Yike");
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        pc.setParent("com.lywl.persontax");
        MysqlGenerator.generatorCode(gc, dsc, pc, "t_compute_tax_non_resident_result_compare", "t_compute_tax_classify_result_compare");
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
