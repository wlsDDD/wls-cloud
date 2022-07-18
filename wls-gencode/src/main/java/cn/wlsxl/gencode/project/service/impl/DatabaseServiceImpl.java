package cn.wlsxl.gencode.project.service.impl;

import cn.wlsxl.gencode.project.entity.Database;
import cn.wlsxl.gencode.project.mapper.DatabaseMapper;
import cn.wlsxl.gencode.project.service.IDatabaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.wlsxl.common.core.enums.CodeInfoEnum;
import cn.wlsxl.common.core.exception.BusinessException;
import cn.wlsxl.common.core.util.Asserts;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.wlsxl.common.web.util.PageUtil;
import java.util.List;

/**
 * <p>
 * 数据库连接配置表 服务实现类
 * </p>
 *
 * @author wls
 * @since 2022-07-18
 */
@Service
public class DatabaseServiceImpl extends ServiceImpl<DatabaseMapper, Database> implements IDatabaseService {

    @Autowired private DatabaseMapper databaseMapper;
    
    /**
     * 分页列表-数据库连接配置表
     *
     * @param database 查询条件
     * @return 分页列表
     */
    @Override
    public IPage<Database> pageDatabase(Database database) {
        return page(PageUtil.getPlusPage(database), Wrappers.lambdaQuery(database));
    }
    
    /**
     * 根据id获取数据库连接配置表详情
     *
     * @param id id
     * @return {@link Database}
     */
    @Override
    public Database getDatabaseById(Long id) {
        return getById(id);
    }
    
    /**
     * 新增-数据库连接配置表
     *
     * @param database 数据库连接配置表
     */
    @Override
    public void insertDatabase(Database database) {
        Asserts.isTrue(save(database), () -> new BusinessException(CodeInfoEnum.DATA_INSERT_ERROR));
    }
    
    /**
     * 修改-数据库连接配置表
     *
     * @param database 数据库连接配置表
     */
    @Override
    public void updateDatabase(Database database) {
        Asserts.isTrue(updateById(database), () -> new BusinessException(CodeInfoEnum.DATA_UPDATE_ERROR));
    }
    
    /**
     * 删除-数据库连接配置表
     *
     * @param ids ids
     */
    @Override
    public void deleteDatabase(List<Long> ids) {
        Asserts.isTrue(removeByIds(ids), () -> new BusinessException(CodeInfoEnum.DATA_DELETE_ERROR));
    }

}
