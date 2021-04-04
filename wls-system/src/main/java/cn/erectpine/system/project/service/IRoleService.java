package cn.erectpine.system.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.erectpine.system.project.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色信息 服务类
 * </p>
 *
 * @author wls
 * @since 2021-04-04
 */
public interface IRoleService extends IService<Role> {

    /**
     * 角色信息-列表
     *
     * @param page      分页参数
     * @param role 查询条件
     * @return 分页列表
     */
    IPage<Role> pageRole(Page<Role> page, Role role);

    /**
     * 根据id获取角色信息表详情
     *
     * @param id id
     * @return {@link Role}
     */
    Role getRoleById(Long id);

    /**
     * 新增-角色信息
     *
     * @param role 角色信息
     */
    void insertRole(Role role);

    /**
     * 修改-角色信息
     *
     * @param role 角色信息
     */
    void updateRole(Role role);

    /**
     * 删除-角色信息
     *
     * @param id id
     */
    void deleteRole(Long id);

}
