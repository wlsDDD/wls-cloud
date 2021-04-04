package cn.erectpine.system.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import cn.erectpine.system.project.entity.UserPost;
import cn.erectpine.system.project.service.IUserPostService;
import org.springframework.web.bind.annotation.RestController;
import cn.erectpine.common.web.BaseController;

/**
 * <p>
 * 用户与岗位关联 控制器
 * </p>
 *
 * @author wls
 * @since 2021-04-04
 */
@RestController
@RequestMapping("/user-post")
public class UserPostController extends BaseController {

    private final IUserPostService userPostService;

    public UserPostController(IUserPostService userPostService) {
        this.userPostService = userPostService;
    }


    /**
     * 用户与岗位关联-分页列表
     */
    @PostMapping("/list")
    public IPage<UserPost> pageUserPost(@RequestBody Page<UserPost> page, UserPost userPost) {
        return userPostService.pageUserPost(page, userPost);
    }

    /**
     * 根据id获取用户与岗位关联详情
     */
    @GetMapping("/{id}")
    public UserPost getUserPostById(@PathVariable Long id) {
        return userPostService.getUserPostById(id);
    }

    /**
     * 新增-用户与岗位关联
     */
    @PostMapping
    public void insertUserPost(@RequestBody UserPost userPost) {
        userPostService.insertUserPost(userPost);
    }

    /**
     * 修改-用户与岗位关联
     */
    @PutMapping
    public void updateUserPost(@RequestBody UserPost userPost) {
        userPostService.updateUserPost(userPost);
    }

    /**
     * 删除-用户与岗位关联
     */
    @DeleteMapping("/{id}")
    public void deleteUserPost(@PathVariable("id") Long id) {
        userPostService.deleteUserPost(id);
    }

}
