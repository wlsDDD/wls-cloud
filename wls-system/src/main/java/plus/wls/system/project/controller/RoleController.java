package plus.wls.system.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plus.wls.common.web.annotation.RequiredParam;
import plus.wls.common.web.pojo.BaseController;
import plus.wls.common.web.pojo.Result;
import plus.wls.system.project.entity.Role;
import plus.wls.system.project.service.impl.RoleServiceImpl;

import java.util.List;

/**
 * <p>
 * 角色信息 控制器
 * </p>
 *
 * @author wls
 * @since 2021-11-19
 */
@RestController
@RequestMapping("/role")
@Slf4j
@AllArgsConstructor
public class RoleController extends BaseController {
    
    private RoleServiceImpl roleService;
    
    
    /**
     * 角色信息-分页列表
     */
    @GetMapping("/page")
    public Result<IPage<Role>> pageRole(Role role) throws Exception {
        log.info("日志-{}", role);
        for (int i = 0; i < 100; i++) {
            roleService.ssx();
        }
        return Result.ok(roleService.pageRole(role));
    }
    
    /**
     * 根据id获取角色信息详情
     */
    @GetMapping("/{id}")
    public Result<Role> getRoleById(@PathVariable("id") Long id) {
        return Result.ok(roleService.getRoleById(id));
    }
    
    /**
     * 新增-角色信息
     */
    @PostMapping
    @RequiredParam({"roleName", "status"})
    public Result<?> insertRole(@RequestBody @Validated Role role) {
        roleService.insertRole(role);
        return Result.ok();
    }
    
    /**
     * 修改-角色信息
     */
    @PutMapping
    public Result<?> updateRole(@RequestBody @Validated Role role) {
        roleService.updateRole(role);
        return Result.ok();
    }
    
    /**
     * 删除-角色信息
     */
    @DeleteMapping("/{ids}")
    public Result<?> deleteRole(@PathVariable("ids") List<Long> ids) {
        roleService.deleteRole(ids);
        return Result.ok();
    }
    
}
