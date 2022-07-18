package cn.wlsxl.gencode.project.service;

import cn.wlsxl.gencode.project.entity.Database;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * <p>
 * 数据库连接配置表 服务类
 * </p>
 *
 * @author wls
 * @since 2022-07-18
 */
public interface IDatabaseService extends IService<Database> {

    /**
     * 分页列表-数据库连接配置表
     *
     * @param database 查询条件
     * @return 分页列表
     */
    IPage<Database> pageDatabase(Database database);
    
    /**
     * 根据id获取数据库连接配置表详情
     *
     * @param id id
     * @return {@link Database}
     */
    Database getDatabaseById(Long id);

    /**
     * 新增-数据库连接配置表
     *
     * @param database 数据库连接配置表
     */
    void insertDatabase(Database database);
    
    /**
     * 修改-数据库连接配置表
     *
     * @param database 数据库连接配置表
     */
    void updateDatabase(Database database);
    
    /**
     * 删除-数据库连接配置表
     *
     * @param ids ids
     */
    void deleteDatabase(List<Long> ids);

}
