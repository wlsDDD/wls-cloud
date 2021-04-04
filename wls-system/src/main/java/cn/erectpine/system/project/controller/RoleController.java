package cn.erectpine.system.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import cn.erectpine.system.project.entity.Role;
import cn.erectpine.system.project.service.IRoleService;
import org.springframework.web.bind.annotation.RestController;
import cn.erectpine.common.web.BaseController;

/**
 * <p>
 * 角色信息 控制器
 * </p>
 *
 * @author wls
 * @since 2021-04-04
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }


    /**
     * 角色信息-分页列表
     */
    @PostMapping("/list")
    public IPage<Role> pageRole(@RequestBody Page<Role> page, Role role) {
        return roleService.pageRole(page, role);
    }

    /**
     * 根据id获取角色信息详情
     */
    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    /**
     * 新增-角色信息
     */
    @PostMapping
    public void insertRole(@RequestBody Role role) {
        roleService.insertRole(role);
    }

    /**
     * 修改-角色信息
     */
    @PutMapping
    public void updateRole(@RequestBody Role role) {
        roleService.updateRole(role);
    }

    /**
     * 删除-角色信息
     */
    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
    }

}
