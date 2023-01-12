package plus.wls.common.gencode;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;

/**
 * 代码生成
 *
 * @author wls
 * @date 2022-05-20 14:52:46
 * @since 1.0.0
 */
public class GenCode {
    
    static String url = "jdbc:mysql://rm-uf66903d9ae3a6k15fo.mysql.rds.aliyuncs.com:3306/wls-cloud-dict?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
    
    public static void main(String[] args) {
        FastAutoGenerator.create(url, "ming", "Ming2020--Yike")
                         .globalConfig(builder -> builder.author("wls")
                                                         .enableSwagger()
                                                         .outputDir(System.getProperty("user.dir"))
                         )
                         .packageConfig(builder -> builder.parent("plus.wls.common.gencode")
                                                          .moduleName("demo")
                         )
                         .strategyConfig(builder -> builder.addInclude("dict_data")
                                                           .addTablePrefix("t_", "c_")
                         )
                         .execute();
    }
    
}
