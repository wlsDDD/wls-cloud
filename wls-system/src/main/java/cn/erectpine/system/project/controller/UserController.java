package cn.erectpine.system.project.controller;

import cn.erectpine.common.web.pojo.BaseController;
import cn.erectpine.common.web.pojo.Result;
import cn.erectpine.common.web.pojo.ValidationList;
import cn.erectpine.dict.api.DictDataApi;
import cn.erectpine.system.project.entity.User;
import cn.erectpine.system.project.service.IUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired private DictDataApi dictDataApi;
    
    static Page<User> page = new Page<>();
    
    static {
        List<User> list = new ArrayList<>();
        list.add(new User().setUserName("wls1").setNickName("nick1"));
        list.add(new User().setUserName("wls2").setNickName("nick2"));
        page.setRecords(list);
    }
    
    
    /**
     * 用户信息-分页列表
     */
    @PostMapping("/page")
    public Result<IPage<User>> pageUser(@RequestBody @Validated List<@Valid User> user) {
        return Result.ok(page);
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
        return Result.ok(page);
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
