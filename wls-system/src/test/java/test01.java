import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import org.junit.jupiter.api.Test;

class test01 {
    
    String url = "jdbc:mysql://rm-uf66903d9ae3a6k15fo.mysql.rds.aliyuncs.com:3306/wls-nacos?" +
            "useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
    
    
    @Test
    void test01() {
        FastAutoGenerator.create(url, "ming", "Ming2020--Yike")
                         .globalConfig(builder -> builder.author("wls")
                                                         .enableSwagger()
                                                         .outputDir(System.getProperty("user.dir"))
                         )
                         .packageConfig(builder -> builder.parent("cn.erectpine.common.gencode")
                                                          .moduleName("demo")
                         )
                         .strategyConfig(builder -> builder.addInclude("dict_data")
                                                           .addTablePrefix("t_", "c_")
                         )
                         .execute();
    }
    
}
