package plus.wls.common.core.constant;

/**
 * 抑制警告常量
 *
 * @author wls
 * @since 2021/09/21 00:04:39
 */
@SuppressWarnings({SuppressWarningConstants.UNUSED})
public interface SuppressWarningConstants {
    
    /**
     * 抑制所有警告
     */
    String ALL = "all";
    /**
     * 抑制没被使用过的代码的警告
     */
    String UNUSED = "unused";
    /**
     * 抑制没有进行类型检查操作的警告
     */
    String UNCHECKED = "unchecked";
    /**
     * 抑制没有传递带有泛型的参数
     */
    String RAWTYPES = "rawtypes";
    /**
     * 有泛型未指定类型
     */
    String RESOURCE = "resource";
    /**
     * 在类路径、源文件路径等中有不存在的路径时的警告
     */
    String PATH = "path";
    /**
     * 抑制与箱/非装箱操作相关的警告
     */
    String BOXING = "boxing";
    /**
     * 抑制映射相关的警告
     */
    String CAST = "cast";
    /**
     * 抑制启用注释的警告
     */
    String DEP_ANN = "dep-ann";
    /**
     * 抑制过期方法警告
     */
    String DEPRECATION = "deprecation";
    /**
     * 在switch语句中，抑制与缺失中断相关的警告
     */
    String FALLTHROUGH = "fallthrough";
    /**
     * 为了抑制警告，相对于最终阻止不返回的警告
     */
    String FINALLY = "finally";
    /**
     * 为了抑制本地隐藏变量的警告
     */
    String HIDING = "hiding";
    /**
     * 为了在switch语句（enum案例）中抑制相对于缺失条目的警告
     */
    String INCOMPLETE_SWITCH = "incomplete-switch";
    /**
     * 忽略非nls格式的字符
     */
    String NLS = "nls";
    /**
     * 忽略对null的操作
     */
    String NULL = "null";
    /**
     * 禁止使用警告或禁止引用的警告
     */
    String RESTRICTION = "restriction";
    /**
     * 忽略在serializable类中没有声明serialVersionUID变量
     */
    String SERIAL = "serial";
    /**
     * 抑制不正确的静态访问方式警告
     */
    String STATIC_ACCESS = "static-access";
    /**
     * 抑制子类没有按最优方法访问内部类的警告
     */
    String SYNTHETIC_ACCESS = "synthetic-access";
    /**
     * 抑制没有权限访问的域的警告
     */
    String UNQUALIFIED_FIELD_ACCESS = "unqualified-field-access";
    
}
