package cn.erectpine.system.project.core.service;

import cn.erectpine.system.project.core.entity.Dept;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 部门 服务类
 * </p>
 *
 * @author wls
 * @since 2021-03-15
 */
public interface IDeptService extends IService<Dept> {
    
    /**
     * 部门-列表
     *
     * @param page 分页参数
     * @param dept 查询条件
     * @return 分页列表
     */
    IPage<Dept> pageDept(Page<Dept> page, Dept dept);
    
    /**
     * 根据id获取部门表详情
     *
     * @param id id
     * @return {@link Dept}
     */
    Dept getDeptById(Long id);
    
    /**
     * 新增-部门
     *
     * @param dept 部门
     */
    void insertDept(Dept dept);
    
    /**
     * 修改-部门
     *
     * @param dept 部门
     */
    void updateDept(Dept dept);
    
    /**
     * 删除-部门
     *
     * @param id id
     */
    void deleteDept(Long id);
    
}
