package plus.wls.system.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import plus.wls.system.project.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色信息 服务类
 * </p>
 *
 * @author wls
 * @since 2021-11-19
 */
public interface IRoleService extends IService<Role> {
    
    /**
     * 角色信息-列表
     *
     * @param role 查询条件
     *
     * @return 分页列表
     */
    IPage<Role> pageRole(Role role);
    
    /**
     * 根据id获取角色信息表详情
     *
     * @param id id
     *
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
     * @param ids ids
     */
    void deleteRole(List<Long> ids);
    
}
