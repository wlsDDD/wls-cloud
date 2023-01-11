package plus.wls.common.core.constant;

/**
 * sql查询条件
 *
 * @author wls
 */
public interface SqlConstants {
    
    /*-----------------------------------------------MYSQL-------------------------------------------------*/
    /**
     * 两边like
     */
    String LIKE = "%s LIKE CONCAT('%%',#{%s},'%%')";
    /**
     * 左边like
     */
    String LIKE_LEFT = "%s LIKE CONCAT('%%',#{%s})";
    /**
     * 右边like
     */
    String LIKE_RIGHT = "%s LIKE CONCAT(#{%s},'%%')";
    /**
     * 等于
     */
    String EQUAL = "%s=#{%s}";
    /**
     * 不等于
     */
    String NOT_EQUAL = "%s&lt;&gt;#{%s}";
    /**
     * 大于
     */
    String GT = "%s&gt;#{%s}";
    /**
     * 大于等于
     */
    String GT_EQ = "%s&gt;=#{%s}";
    /**
     * 小于
     */
    String LT = "%s&lt;#{%s}";
    /**
     * 小于等于
     */
    String LT_EQ = "%s&gt;=#{%s}";
    
    
    /*-----------------------------------------------ORACLE-------------------------------------------------*/
    /**
     * oracle两边like查询
     */
    String ORACLE_LIKE = "%s LIKE CONCAT(CONCAT('%%',#{%s}),'%%')";
    /**
     * oracle 至多取一行数据
     */
    String ORACLE_LIMIT_ONE = "AND ROWNUM <= 1";
    
}
