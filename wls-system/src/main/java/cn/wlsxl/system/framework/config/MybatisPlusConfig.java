package cn.wlsxl.system.framework.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 *
 * @author wls
 * @since 2021-01-11 13:53
 */
@MapperScan("cn.wlsxl.system.project.mapper")
@Configuration
public class MybatisPlusConfig {
    
    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false
     * 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
    
    //    @Bean
    //    public MybatisPlusInterceptor mybatisPlusInterceptor() {
    //        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    //        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
    //        dynamicTableNameInnerInterceptor.setTableNameHandler((sql, tableName) -> {
    //            // 获取参数方法
    //            Map<String, Object> paramMap = RequestDataHelper.getRequestData();
    //            paramMap.forEach((k, v) -> System.err.println(k + "----" + v));
    //
    //            String year = "_2018";
    //            int random = new Random().nextInt(10);
    //            if (random % 2 == 1) {
    //                year = "_2019";
    //            }
    //            return tableName + year;
    //        });
    //        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
    //        // 3.4.3.2 作废该方式
    //        // dynamicTableNameInnerInterceptor.setTableNameHandlerMap(map);
    //        return interceptor;
    //    }
    
    //    @Bean
    //    public ConfigurationCustomizer configurationCustomizer() {
    //        return configuration -> configuration.setUseDeprecatedExecutor(false)
    //    }
    
}
