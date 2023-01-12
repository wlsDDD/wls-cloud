package plus.wls.gencode;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;
import java.util.LinkedHashMap;

public class GenCode {
    
    public static void main(String[] args) {
        String outputDir = System.getProperty("user.dir") + "/wls-gencode";
        // String outputDir = "";
        String moduleName = "";
        FastAutoGenerator.create("jdbc:mysql://159.75.93.83:3306/wls-gencode?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&allowMultiQueries=true",
                                 "root", "wls-mysql-tencent")
                         .globalConfig(builder -> builder.author("wls").fileOverride().disableOpenDir().outputDir(outputDir + "/src/main/java"))
                         .injectionConfig(builder -> builder.customMap(new LinkedHashMap<>()))
                         .templateConfig(
                                 builder -> builder.controller("template/controller.java")
                                                   .entity("template/entity.java")
                                                   .service("template/service.java")
                                                   .serviceImpl("template/serviceImpl.java")
                                                   .mapper("template/mapper.java")
                                                   .mapperXml("template/mapper.xml")
                         ).packageConfig(
                                 builder -> builder.parent("plus.wls.gencode.project").moduleName(moduleName)
                                                   .pathInfo(Collections.singletonMap(OutputFile.mapperXml, outputDir + "/src/main/resources/mapper/" + moduleName))
                         ).strategyConfig(
                                 builder -> builder.addInclude("gen_database").addTablePrefix("gen_").entityBuilder().enableLombok().enableChainModel()
                         ).execute();
        
    }
    
}
