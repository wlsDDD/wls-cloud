package plus.wls.common.core.constant;

import plus.wls.common.core.enums.ActiveEnum;

/**
 * 全局常量信息
 *
 * @author wls
 * @since 2021/4/3 18:30
 */
public class GlobalConstants {
    /**
     * 项目名称
     */
    public static String projectName = "wls-cloud";
    public static final String basePackage = "plus.wls";
    /**
     * 服务名称
     */
    public static String serviceName;
    /**
     * 项目运行环境
     */
    public static ActiveEnum active;
    /**
     * 堆栈信息过滤关键字
     */
    public static String stackFilter;
    
    // @Autowired
    // public static void setProjectName( projectName) {
    //     GlobalConstants.projectName = projectName;
    // }
    //
    // @Autowired
    // public static void setServiceName(String serviceName) {
    //     GlobalConstants.serviceName = serviceName;
    // }
    //
    // @Autowired
    // public static void setActive(ActiveEnum active) {
    //     GlobalConstants.active = active;
    // }
    //
    // @Autowired
    // public static void setStackFilter(String stackFilter) {
    //     GlobalConstants.stackFilter = stackFilter;
    // }
    
}
