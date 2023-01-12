import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedHashMap;

class Test01 {
    
    @Test
    void test01() {
        String outputDir = System.getProperty("user.dir");
        String moduleName = "";
        FastAutoGenerator.create("jdbc:mysql://159.75.93.83:3306/wls-gencode?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&allowMultiQueries=true",
                                 "root", "wls-mysql-tencent")
                         .globalConfig(builder ->
                                 builder.author("wls").fileOverride().outputDir(outputDir + "/src/main/java"))
                         .injectionConfig(builder -> builder.customMap(new LinkedHashMap<>()))
                         .templateConfig(builder -> builder.controller("")
                                                           .entity("")
                                                           .service("")
                                                           .serviceImpl("")
                                                           .mapper("")
                                                           .mapperXml("")
                         )
                         .packageConfig(builder ->
                                 builder.parent("plus.wls.gencode.project").moduleName(moduleName)
                                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, outputDir + "/src/main/resources/mapper/" + moduleName)))
                         .strategyConfig(builder ->
                                 builder.addInclude("gen_database").addTablePrefix("gen_"))
                         .execute();
        
    }
    
}
