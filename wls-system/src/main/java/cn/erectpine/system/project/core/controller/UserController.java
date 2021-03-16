package cn.erectpine.system.project.core.controller;

import cn.erectpine.common.web.ResponseTemplate;
import cn.erectpine.system.project.core.entity.User;
import cn.erectpine.system.project.core.service.IUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author wls
 * @since 2021-01-20
 */
@RefreshScope
@RestController
@RequestMapping("/core/user")
public class UserController {
    
    @Autowired IUserService userService;
    
    
    /**
     * 用户信息-分页列表
     */
    @PostMapping("/list")
    public ResponseTemplate pageUser(@RequestBody Page<User> page, User user) {
        if (user.getUserName() == null) {
            throw new RuntimeException("模拟一个异常");
        }
        return ResponseTemplate.success(userService.pageUser(page, user));
    }
    
    /**
     * 根据id获取用户信息详情
     */
    @GetMapping("/{id}")
    public ResponseTemplate getUserById(@PathVariable Long id) {
        return ResponseTemplate.success(userService.getUserById(id));
    }
    
    /**
     * 新增-用户信息
     */
    @PostMapping
    public ResponseTemplate insertUser(@RequestBody User user) {
        userService.insertUser(user);
        return ResponseTemplate.success();
    }
    
    /**
     * 修改-用户信息
     */
    @PutMapping
    public ResponseTemplate updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseTemplate.success();
    }
    
    /**
     * 删除-用户信息
     */
    @DeleteMapping("/{id}")
    public ResponseTemplate deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseTemplate.success();
    }
    
}
