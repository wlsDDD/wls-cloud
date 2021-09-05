package cn.erectpine.system.project.service.impl;

import cn.erectpine.common.core.enums.CodeMsgEnum;
import cn.erectpine.common.core.util.pine.PineAssert;
import cn.erectpine.common.web.exception.BusinessException;
import cn.erectpine.system.project.entity.Role;
import cn.erectpine.system.project.entity.User;
import cn.erectpine.system.project.mapper.UserMapper;
import cn.erectpine.system.project.service.IRoleService;
import cn.erectpine.system.project.service.IUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    private final UserMapper userMapper;
    
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    
    /**
     * 用户信息-列表
     *
     * @param page 分页参数
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
    
    @Autowired IRoleService roleService;
    
    /**
     * 新增-用户信息
     *
     * @param user 用户信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUser(User user) {
//        addUser(user);
        roleService.insertRole(new Role().setRoleName("roleName").setRoleKey("role"));
//        addUser(user.setUserName("null"));
//        PineAssert.isTrue(save(user), () -> new BusinessException(CodeMsgEnum.DATA_INSERT_ERROR));
    }
    
    private void addUser(User user) {
        PineAssert.notBlank(user.getUserName(), "姓名不可为空");
        save(user);
    }
    
    /**
     * 修改-用户信息
     *
     * @param user 用户信息
     */
    @Override
    public void updateUser(User user) {
        PineAssert.isTrue(updateById(user), () -> new BusinessException(CodeMsgEnum.DATA_UPDATE_ERROR));
    }
    
    /**
     * 删除-用户信息
     *
     * @param id id
     */
    @Override
    public void deleteUser(Long id) {
        PineAssert.isTrue(removeById(id), () -> new BusinessException(CodeMsgEnum.DATA_DELETE_ERROR));
    }
    
}
