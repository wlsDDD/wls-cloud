package cn.erectpine.system.project.core.service.impl;

import cn.erectpine.system.project.core.entity.User;
import cn.erectpine.system.project.core.mapper.UserMapper;
import cn.erectpine.system.project.core.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author wls
 * @since 2021-01-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired UserMapper userMapper;


    /**
     * 用户信息-列表
     *
     * @param page  分页参数
     * @param user 查询条件
     * @return 分页列表
     */
    @Override
    public IPage<User> pageUser(Page<User> page, User user) {
        return page(page, Wrappers.lambdaQuery(user));
    }

    /**
     * 根据id获取用户信息表详情
     *
     * @param id id
     * @return {@link User}
     */
    @Override
    public User getUserById(Long id) {
        return getById(id);
    }

    /**
     * 新增-用户信息
     *
     * @param user 用户信息
     */
    @Override
    public void insertUser(User user) {
        save(user);
    }

    /**
     * 修改-用户信息
     *
     * @param user 用户信息
     */
    @Override
    public void updateUser(User user) {
        updateById(user);
    }

    /**
     * 删除-用户信息
     *
     * @param id id
     */
    @Override
    public void deleteUser(Long id) {
        removeById(id);
    }

}
