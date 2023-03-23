package plus.wls.common.web.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.IllegalSQLInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import plus.wls.common.core.constant.GlobalConstants;

/**
 * MybatisPlus配置
 *
 * @author wls
 * @since 2021-01-11 13:53
 */
@MapperScan(GlobalConstants.basePackage + ".**.mapper")
@Configuration
public class MybatisPlusConfig {
    
    ThreadLocal<Boolean> threadLocal = new InheritableThreadLocal<>();
    
    /**
     * mybatis-plus 插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor plugin = new MybatisPlusInterceptor();
        // 多租户插件
        // plugin.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
        //     @Override
        //     public Expression getTenantId() {
        //         return new LongValue(1);
        //     }
        //
        //     // 这是 default 方法,默认返回 false 表示所有表都需要拼多租户条件
        //     @Override
        //     public boolean ignoreTable(String tableName) {
        //         return threadLocal.get();
        //     }
        // }));
        // 分页插件
        plugin.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // sql 性能规范插件
        plugin.addInnerInterceptor(new IllegalSQLInnerInterceptor());
        // 动态表名插件
        // DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        // dynamicTableNameInnerInterceptor.setTableNameHandler((sql, tableName) -> null);
        // plugin.addInnerInterceptor(dynamicTableNameInnerInterceptor);
        return plugin;
    }
    
}
