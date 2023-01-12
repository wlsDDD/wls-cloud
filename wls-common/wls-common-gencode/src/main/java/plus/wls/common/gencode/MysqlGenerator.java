// package plus.wls.common.gencode;
//
// import plus.wls.common.core.enums.CodeInfoEnum;
// import plus.wls.common.core.exception.BusinessException;
// import plus.wls.common.core.util.Asserts;
// import com.baomidou.mybatisplus.annotation.DbType;
// import com.baomidou.mybatisplus.annotation.IdType;
// import com.baomidou.mybatisplus.core.toolkit.StringPool;
// import com.baomidou.mybatisplus.generator.AutoGenerator;
// import com.baomidou.mybatisplus.generator.InjectionConfig;
// import com.baomidou.mybatisplus.generator.config.*;
// import com.baomidou.mybatisplus.generator.config.po.TableInfo;
// import com.baomidou.mybatisplus.generator.config.rules.DateType;
// import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
// import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
//
// /**
// * 自定义代码生成器
// *
// * @author wls
// */
// public class MysqlGenerator {
//
//    /**
//     * 去除表前缀
//     */
//    public static String tablePrefix = "sys_";
//
//    /**
//     * 模块名称
//     */
//    public static String moduleName = "";
//    /**
//     * 本项目路径
//     */
//    public static String path = "";
//    /**
//     * 包路径
//     */
//    public static String packagePath = "plus.wls.dict.project";
//
//    /**
//     * 数据源-URL
//     */
//    public static String dataSourceUrl = "jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
//    /**
//     * 数据源-用户名
//     */
//    public static String dataSourceUsername = "root";
//    /**
//     * 数据源-密码
//     */
//    public static String dataSourcePassword = "Wls-root";
//    /**
//     * 数据源-驱动
//     */
//    public static String dataSourceDriverName = "com.mysql.cj.jdbc.Driver";
//
//    static GlobalConfig gc = new GlobalConfig();
//    static DataSourceConfig dsc = new DataSourceConfig();
//    static PackageConfig pc = new PackageConfig();
//    static String projectPath = System.getProperty("user.dir");
//
//    static {
//        pc.setModuleName(moduleName);
//        pc.setParent(packagePath);
//    }
//
//    static {
//        gc.setOutputDir(projectPath + path + "/src/main/java");
//        gc.setAuthor("wls");
//        gc.setOpen(false);
//        // 实体属性 Swagger2 注解
//        gc.setSwagger2(false);
//        gc.setDateType(DateType.TIME_PACK);
//        gc.setIdType(IdType.AUTO);
//        gc.setFileOverride(false);
//    }
//
//    static {
//        dsc.setUrl(dataSourceUrl);
//        dsc.setDriverName(dataSourceDriverName);
//        dsc.setDbType(DbType.MYSQL);
//        dsc.setUsername(dataSourceUsername);
//        dsc.setPassword(dataSourcePassword);
//
//    }
//
//    public static void generatorCode(String path, String... tableName) {
//        MysqlGenerator.packagePath = path;
//        pc.setParent(packagePath);
//        generatorCode(gc, dsc, pc, tableName);
//    }
//
//    public static void generatorCode(DataSourceConfig dataSourceConfig, PackageConfig packageConfig, String... tableName) {
//        generatorCode(gc, dataSourceConfig, packageConfig, tableName);
//    }
//
//    /**
//     * 代码生成
//     *
//     * @param tableName 表名称，多个逗号隔开 支持正则表达式
//     */
//    public static void generatorCode(GlobalConfig config, DataSourceConfig dataSourceConfig, PackageConfig packageConfig, String... tableName) {
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//        // 全局配置
//        mpg.setGlobalConfig(config);
//        // 数据源配置
//        mpg.setDataSource(dataSourceConfig);
//        // 包配置
//        mpg.setPackageInfo(packageConfig);
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                Map<String, Object> map = new HashMap<>(16);
//                map.put("Result", "plus.wls.common.web.pojo.Result");
//                map.put("PageUtil", "plus.wls.common.web.util.PageUtil");
//                map.put("PineAssert", Asserts.class.getName());
//                map.put("BusinessException", BusinessException.class.getName());
//                map.put("CodeMsgEnum", CodeInfoEnum.class.getName());
//                this.setMap(map);
//            }
//        };
//        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
//        // 如果模板引擎是 velocity
// //         String templatePath = "/templates/mapper.xml.vm";
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + path + "/src/main/resources/mapper/" + moduleName + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//        // 配置模板
//        TemplateConfig templateConfig = new TemplateConfig();
//        // 配置自定义输出模板
//        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//        templateConfig.setEntity("ftl/mysql/entity.java");
//        templateConfig.setController("ftl/mysql/controller.java");
//        templateConfig.setService("ftl/mysql/service.java");
//        templateConfig.setServiceImpl("ftl/mysql/serviceImpl.java");
//        templateConfig.setMapper("ftl/mysql/mapper.java");
//        templateConfig.setXml(null);
//        mpg.setTemplate(templateConfig);
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setChainModel(true);
//        strategy.setEntityLombokModel(true);
//        strategy.setRestControllerStyle(true);
//        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
//        // 是否生成实体时，生成字段注解
//        // strategy.setEntityTableFieldAnnotationEnable(true)
//        // 公共父类
//        strategy.setSuperControllerClass("plus.wls.common.web.pojo.BaseController");
//        strategy.setSuperEntityClass("plus.wls.common.web.pojo.BaseEntity");
//        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("create_time", "update_time", "create_by", "update_by", "is_deleted");
//        strategy.setInclude(tableName);
//        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(tablePrefix);
//        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//        mpg.execute();
//    }
//
// }
//
