// import plus.wls.common.gencode.MysqlGenerator;
// import com.baomidou.mybatisplus.annotation.DbType;
// import com.baomidou.mybatisplus.annotation.IdType;
// import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
// import com.baomidou.mybatisplus.generator.config.GlobalConfig;
// import com.baomidou.mybatisplus.generator.config.PackageConfig;
// import com.baomidou.mybatisplus.generator.config.rules.DateType;
// import org.junit.jupiter.api.Test;
//
// /**
// * @author wls
// * @since 2021/9/4 22:55
// */
// public class GenCode {
//
//    @Test
//    public void test01() {
//        MysqlGenerator.generatorCode("plus.wls.system.project", "sys_user", "sys_role");
//    }
//
//    @Test
//    public void test08() {
//        GlobalConfig gc = new GlobalConfig();
//        gc.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
//        gc.setAuthor("wls");
//        gc.setOpen(false);
//        // 实体属性 Swagger2 注解
//        gc.setSwagger2(true);
//        gc.setDateType(DateType.TIME_PACK);
//        gc.setIdType(IdType.AUTO);
//        gc.setFileOverride(false);
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://rm-uf66903d9ae3a6k15fo.mysql.rds.aliyuncs.com:3306/dev_persontax?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&allowPublicKeyRetrieval=true");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setDbType(DbType.MYSQL);
//        dsc.setUsername("ming");
//        dsc.setPassword("Ming2020--Yike");
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("");
//        pc.setParent("com.lywl.persontax");
//        MysqlGenerator.generatorCode(gc, dsc, pc, "t_compute_tax_non_resident_result_compare", "t_compute_tax_classify_result_compare");
//    }
//
// }
