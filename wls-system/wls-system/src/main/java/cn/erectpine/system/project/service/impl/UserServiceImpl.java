package cn.erectpine.system.project.service.impl;

import cn.erectpine.system.project.entity.User;
import cn.erectpine.system.project.mapper.UserMapper;
import cn.erectpine.system.project.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import cn.erectpine.common.web.exception.BusinessException;
import cn.erectpine.common.enums.CodeMsgEnum;
import cn.erectpine.common.util.Assert;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author wls
 * @since 2021-04-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


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
        Assert.isTrue(save(user), () -> new BusinessException(CodeMsgEnum.DATA_INSERT_ERROR));
    }

    /**
     * 修改-用户信息
     *
     * @param user 用户信息
     */
    @Override
    public void updateUser(User user) {
        Assert.isTrue(updateById(user), () -> new BusinessException(CodeMsgEnum.DATA_UPDATE_ERROR));
    }

    /**
     * 删除-用户信息
     *
     * @param id id
     */
    @Override
    public void deleteUser(Long id) {
        Assert.isTrue(removeById(id), () -> new BusinessException(CodeMsgEnum.DATA_DELETE_ERROR));
    }

}
