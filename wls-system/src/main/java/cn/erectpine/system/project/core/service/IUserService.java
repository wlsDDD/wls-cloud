package cn.erectpine.system.project.core.service;

import cn.erectpine.system.project.core.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author wls
 * @since 2021-01-20
 */
public interface IUserService extends IService<User> {
    
    /**
     * 用户信息-列表
     *
     * @param page 分页参数
     * @param user 查询条件
     * @return 分页列表
     */
    IPage<User> pageUser(Page<User> page, User user);
    
    /**
     * 根据id获取用户信息表详情
     *
     * @param id id
     * @return {@link User}
     */
    User getUserById(Long id);
    
    /**
     * 新增-用户信息
     *
     * @param user 用户信息
     */
    void insertUser(User user);
    
    /**
     * 修改-用户信息
     *
     * @param user 用户信息
     */
    void updateUser(User user);
    
    /**
     * 删除-用户信息
     *
     * @param id id
     */
    void deleteUser(Long id);
    
}
