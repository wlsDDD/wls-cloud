package cn.erectpine.system.project.service.impl;

import cn.erectpine.system.project.entity.Dept;
import cn.erectpine.system.project.mapper.DeptMapper;
import cn.erectpine.system.project.service.IDeptService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author wls
 * @since 2021-03-15
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {
    
    @Autowired DeptMapper deptMapper;
    
    
    /**
     * 部门-列表
     *
     * @param page 分页参数
     * @param dept 查询条件
     * @return 分页列表
     */
    @Override
    public IPage<Dept> pageDept(Page<Dept> page, Dept dept) {
        return page(page, Wrappers.lambdaQuery(dept));
    }
    
    /**
     * 根据id获取部门表详情
     *
     * @param id id
     * @return {@link Dept}
     */
    @Override
    public Dept getDeptById(Long id) {
        return getById(id);
    }
    
    /**
     * 新增-部门
     *
     * @param dept 部门
     */
    @Override
    public void insertDept(Dept dept) {
        save(dept);
    }
    
    /**
     * 修改-部门
     *
     * @param dept 部门
     */
    @Override
    public void updateDept(Dept dept) {
        updateById(dept);
    }
    
    /**
     * 删除-部门
     *
     * @param id id
     */
    @Override
    public void deleteDept(Long id) {
        removeById(id);
    }
    
}
