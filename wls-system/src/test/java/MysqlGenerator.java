import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义代码生成器
 *
 * @Author wls
 */
public class MysqlGenerator {
    
    /**
     * 表名称，多个逗号隔开 支持正则表达式
     */
    static String[] tableName = {"sys_user_post", "sys_user"};
    /**
     * 去除表前缀
     */
    static String tablePrefix = "sys_";
    
    /**
     * 模块名称
     */
    static String moduleName = "";
    /**
     * 本项目路径
     */
    static String path = "/wls-system";
    /**
     * 包路径
     */
    static String packagePath = "cn.erectpine.system.project";
    
    /**
     * 数据源-URL
     */
    static String dataSourceUrl = "jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
    /**
     * 数据源-用户名
     */
    static String dataSourceUsername = "root";
    /**
     * 数据源-密码
     */
    static String dataSourcePassword = "Wls-root";
    /**
     * 数据源-驱动
     */
    static String dataSourceDriverName = "com.mysql.cj.jdbc.Driver";
    
    
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setOutputDir(projectPath + path + "/src/main/java");
        gc.setAuthor("wls");
        gc.setOpen(false);
        // 实体属性 Swagger2 注解
//        gc.setSwagger2(true);
        gc.setDateType(DateType.TIME_PACK);
        gc.setIdType(IdType.AUTO);
        mpg.setGlobalConfig(gc);
        
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dataSourceUrl);
        // dsc.setSchemaName("public");
        dsc.setDriverName(dataSourceDriverName);
        dsc.setDbType(DbType.MYSQL);
        dsc.setUsername(dataSourceUsername);
        dsc.setPassword(dataSourcePassword);
        mpg.setDataSource(dsc);
        
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(packagePath);
        mpg.setPackageInfo(pc);
        
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("ResponseTemplate", "cn.erectpine.common.web.ResponseTemplate");
                map.put("Assert", "cn.erectpine.common.util.Assert");
                map.put("BusinessException", "cn.erectpine.common.web.exception.BusinessException");
                map.put("CodeMsgEnum", "cn.erectpine.common.enums.CodeMsgEnum");
                this.setMap(map);
            }
        };
        
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
//         String templatePath = "/templates/mapper.xml.vm";
        
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + path + "/src/main/resources/mapper/" + moduleName + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        
        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("ftl/mysql/entity.java");
        templateConfig.setController("ftl/mysql/controller.java");
        templateConfig.setService("ftl/mysql/service.java");
        templateConfig.setServiceImpl("ftl/mysql/serviceImpl.java");
        
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setChainModel(true);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        
        // 是否生成实体时，生成字段注解
//        strategy.setEntityTableFieldAnnotationEnable(true);
        // 公共父类
        strategy.setSuperControllerClass("cn.erectpine.common.web.BaseController");
        strategy.setSuperEntityClass("cn.erectpine.common.web.BaseEntity");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("create_time", "update_time", "create_by", "update_by");
        strategy.setInclude(tableName);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(tablePrefix);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
    
}