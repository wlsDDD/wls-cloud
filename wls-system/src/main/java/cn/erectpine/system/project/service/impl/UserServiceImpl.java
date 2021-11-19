package cn.erectpine.system.project.service.impl;

import cn.erectpine.system.project.entity.User;
import cn.erectpine.system.project.mapper.UserMapper;
import cn.erectpine.system.project.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.erectpine.common.core.exception.BusinessException;
import cn.erectpine.common.core.enums.CodeInfoEnum;
import cn.erectpine.common.core.util.pine.Asserts;
import cn.erectpine.common.web.util.PageUtil;
import java.util.List;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author wls
 * @since 2021-11-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired private UserMapper userMapper;

    /**
     * 用户信息-列表
     *
     * @param user 查询条件
     * @return 分页列表
     */
    @Override
    public IPage<User> pageUser(User user) {
        return page(PageUtil.getPlusPage(user), Wrappers.lambdaQuery(user));
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
        Asserts.isTrue(save(user), () -> new BusinessException(CodeInfoEnum.DATA_INSERT_ERROR));
    }

    /**
     * 修改-用户信息
     *
     * @param user 用户信息
     */
    @Override
    public void updateUser(User user) {
        Asserts.isTrue(updateById(user), () -> new BusinessException(CodeInfoEnum.DATA_UPDATE_ERROR));
    }

    /**
     * 删除-用户信息
     *
     * @param ids ids
     */
    @Override
    public void deleteUser(List<Long> ids) {
        Asserts.isTrue(removeByIds(ids), () -> new BusinessException(CodeInfoEnum.DATA_DELETE_ERROR));
    }

}
