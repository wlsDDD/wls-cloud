package cn.erectpine.system.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import cn.erectpine.system.project.entity.User;
import cn.erectpine.system.project.service.IUserService;
import org.springframework.web.bind.annotation.RestController;
import cn.erectpine.common.web.BaseController;

/**
 * <p>
 * 用户信息 控制器
 * </p>
 *
 * @author wls
 * @since 2021-04-04
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    /**
     * 用户信息-分页列表
     */
    @PostMapping("/list")
    public IPage<User> pageUser(@RequestBody Page<User> page, User user) {
        return userService.pageUser(page, user);
    }

    /**
     * 根据id获取用户信息详情
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * 新增-用户信息
     */
    @PostMapping
    public void insertUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    /**
     * 修改-用户信息
     */
    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    /**
     * 删除-用户信息
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
