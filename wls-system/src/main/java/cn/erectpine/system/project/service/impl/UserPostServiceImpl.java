package cn.erectpine.system.project.service.impl;

import cn.erectpine.common.core.enums.CodeInfoEnum;
import cn.erectpine.common.core.util.pine.Asserts;
import cn.erectpine.common.web.exception.BusinessException;
import cn.erectpine.system.project.entity.UserPost;
import cn.erectpine.system.project.mapper.UserPostMapper;
import cn.erectpine.system.project.service.IUserPostService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与岗位关联 服务实现类
 * </p>
 *
 * @author wls
 * @since 2021-04-04
 */
@Service
public class UserPostServiceImpl extends ServiceImpl<UserPostMapper, UserPost> implements IUserPostService {
    
    private final UserPostMapper userPostMapper;
    
    public UserPostServiceImpl(UserPostMapper userPostMapper) {
        this.userPostMapper = userPostMapper;
    }
    
    
    /**
     * 用户与岗位关联-列表
     *
     * @param page     分页参数
     * @param userPost 查询条件
     * @return 分页列表
     */
    @Override
    public IPage<UserPost> pageUserPost(Page<UserPost> page, UserPost userPost) {
        return page(page, Wrappers.lambdaQuery(userPost));
    }
    
    /**
     * 根据id获取用户与岗位关联表详情
     *
     * @param id id
     * @return {@link UserPost}
     */
    @Override
    public UserPost getUserPostById(Long id) {
        return getById(id);
    }
    
    /**
     * 新增-用户与岗位关联
     *
     * @param userPost 用户与岗位关联
     */
    @Override
    public void insertUserPost(UserPost userPost) {
        Asserts.isTrue(save(userPost), () -> new BusinessException(CodeInfoEnum.DATA_INSERT_ERROR));
    }
    
    /**
     * 修改-用户与岗位关联
     *
     * @param userPost 用户与岗位关联
     */
    @Override
    public void updateUserPost(UserPost userPost) {
        Asserts.isTrue(updateById(userPost), () -> new BusinessException(CodeInfoEnum.DATA_UPDATE_ERROR));
    }
    
    /**
     * 删除-用户与岗位关联
     *
     * @param id id
     */
    @Override
    public void deleteUserPost(Long id) {
        Asserts.isTrue(removeById(id), () -> new BusinessException(CodeInfoEnum.DATA_DELETE_ERROR));
    }
    
}
