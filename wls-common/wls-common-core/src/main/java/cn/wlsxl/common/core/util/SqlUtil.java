package cn.wlsxl.common.core.util;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;

/**
 * sql工具类
 *
 * @author wls
 * @since 2021/09/20 23:44:45
 */
public class SqlUtil {
    
    /**
     * sql填补
     *
     * @param sql   sql
     * @param param 参数
     *
     * @return {@link String}
     */
    public static String sqlFill(String sql, String param) {
        Object[] params = Arrays.stream(param.split(",")).map(s -> StrUtil.subBefore(s, "(", false)).toArray();
        return StrUtil.format(sql.replace("?", "{}"), params);
    }
    
}
