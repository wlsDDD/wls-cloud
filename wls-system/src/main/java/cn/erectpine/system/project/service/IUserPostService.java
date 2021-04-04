package cn.erectpine.system.project.service;

import cn.erectpine.system.project.entity.UserPost;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户与岗位关联 服务类
 * </p>
 *
 * @author wls
 * @since 2021-04-04
 */
public interface IUserPostService extends IService<UserPost> {
    
    /**
     * 用户与岗位关联-列表
     *
     * @param page     分页参数
     * @param userPost 查询条件
     * @return 分页列表
     */
    IPage<UserPost> pageUserPost(Page<UserPost> page, UserPost userPost);
    
    /**
     * 根据id获取用户与岗位关联表详情
     *
     * @param id id
     * @return {@link UserPost}
     */
    UserPost getUserPostById(Long id);
    
    /**
     * 新增-用户与岗位关联
     *
     * @param userPost 用户与岗位关联
     */
    void insertUserPost(UserPost userPost);
    
    /**
     * 修改-用户与岗位关联
     *
     * @param userPost 用户与岗位关联
     */
    void updateUserPost(UserPost userPost);
    
    /**
     * 删除-用户与岗位关联
     *
     * @param id id
     */
    void deleteUserPost(Long id);
    
}
