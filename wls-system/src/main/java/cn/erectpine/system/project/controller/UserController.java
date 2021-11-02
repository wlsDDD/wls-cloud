package cn.erectpine.system.project.controller;

import cn.erectpine.common.core.util.pine.Pines;
import cn.erectpine.common.web.pojo.BaseController;
import cn.erectpine.common.web.pojo.Result;
import cn.erectpine.system.project.entity.User;
import cn.erectpine.system.project.service.IUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户信息 控制器
 * </p>
 *
 * @author wls
 * @since 2021-10-14
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired private IUserService userService;
    
    
    @ApiOperation("用户信息-分页列表")
    @PostMapping("/page")
    public Result<IPage<User>> pageUser(@RequestBody @Validated User user) {
        return Result.ok(userService.pageUser(Pines.copyBean(user, new User())));
    }

    @ApiOperation("根据id获取用户信息详情")
    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable("id") Long id) {
        return Result.ok(userService.getUserById(id));
    }

    @ApiOperation("新增-用户信息")
    @PostMapping
    public Result<?> insertUser(@RequestBody @Validated User user) {
        userService.insertUser(user);
        return Result.ok();
    }

    @ApiOperation("修改-用户信息")
    @PutMapping
    public Result<?> updateUser(@RequestBody @Validated User user) {
        userService.updateUser(user);
        return Result.ok();
    }

    @ApiOperation("删除-用户信息")
    @DeleteMapping("/{ids}")
    public Result<?> deleteUser(@PathVariable("ids") List<Long> ids) {
        userService.deleteUser(ids);
        return Result.ok();
    }

}
