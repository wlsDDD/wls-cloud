package cn.erectpine.system.project.service.impl;

import cn.erectpine.common.core.enums.CodeInfoEnum;
import cn.erectpine.common.core.exception.BusinessException;
import cn.erectpine.common.core.util.pine.Asserts;
import cn.erectpine.common.redis.annotation.DistributedLock;
import cn.erectpine.system.project.entity.Role;
import cn.erectpine.system.project.entity.User;
import cn.erectpine.system.project.mapper.UserMapper;
import cn.erectpine.system.project.service.IRoleService;
import cn.erectpine.system.project.service.IUserService;
import cn.hutool.extra.spring.SpringUtil;
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
    @Autowired IRoleService roleService;
    
    
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
    public IPage<User> pageUser(Page<User> page, User user) throws InterruptedException {
//        for (int i = 0; i < 120; i++) {
//            Thread.sleep(1000);
//        }
        SpringUtil.getBean(this.getClass()).getUserById(user.getUserId());
        return page(page, Wrappers.lambdaQuery(user));
    }
    
    /**
     * 根据id获取用户信息表详情
     *
     * @param id id
     * @return {@link User}
     */
    @Override
    @DistributedLock
    public User getUserById(Long id) {
        return getById(id);
    }
    
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
//        Asserts.isTrue(save(user), () -> new BusinessException(CodeMsgEnum.DATA_INSERT_ERROR));
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
     * @param id id
     */
    @Override
    public void deleteUser(Long id) {
        Asserts.isTrue(removeById(id), () -> new BusinessException(CodeInfoEnum.DATA_DELETE_ERROR));
    }
    
    private void addUser(User user) {
        Asserts.notBlank(user.getUserName(), "姓名不可为空");
        save(user);
    }
    
}
