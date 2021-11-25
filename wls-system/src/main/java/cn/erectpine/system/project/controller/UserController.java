package cn.erectpine.system.project.controller;

import cn.erectpine.system.project.entity.valid.ValidationList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import cn.erectpine.system.project.entity.User;
import cn.erectpine.system.project.service.IUserService;
import cn.erectpine.common.web.pojo.Result;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import cn.erectpine.common.web.pojo.BaseController;

/**
 * <p>
 * 用户信息 控制器
 * </p>
 *
 * @author wls
 * @since 2021-11-19
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired private IUserService userService;


    /**
     * 用户信息-分页列表
     */
    @GetMapping("/page")
    public Result<IPage<User>> pageUser(@RequestBody @Validated ValidationList<User> user) {
        return Result.ok(userService.pageUser(user.get(0)));
    }

    /**
     * 根据id获取用户信息详情
     */
    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable("id") Long id) {
        return Result.ok(userService.getUserById(id));
    }

    /**
     * 新增-用户信息
     */
    @PostMapping
    public Result<?> insertUser(@RequestBody @Validated ValidationList<User> user) {
        userService.insertUser(user.get(0));
        return Result.ok();
    }

    /**
     * 修改-用户信息
     */
    @PutMapping
    public Result<?> updateUser(@RequestBody @Validated User user) {
        userService.updateUser(user);
        return Result.ok();
    }

    /**
     * 删除-用户信息
     */
    @DeleteMapping("/{ids}")
    public Result<?> deleteUser(@PathVariable("ids") List<Long> ids) {
        userService.deleteUser(ids);
        return Result.ok();
    }

}
