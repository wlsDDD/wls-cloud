package cn.erectpine.system.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.erectpine.system.project.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author wls
 * @since 2021-10-14
 */
public interface IUserService extends IService<User> {

    /**
     * 用户信息-列表
     *
     * @param user 查询条件
     * @return 分页列表
     */
    IPage<User> pageUser(User user);

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
     * @param ids ids
     */
    void deleteUser(List<Long> ids);

}
