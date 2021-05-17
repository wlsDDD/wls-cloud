package cn.erectpine.system.project.controller;

import cn.erectpine.common.web.HttpResult;
import cn.erectpine.system.project.entity.Dept;
import cn.erectpine.system.project.service.IDeptService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author wls
 * @since 2021-03-15
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    
    private final IDeptService deptService;
    
    public DeptController(IDeptService deptService) {
        this.deptService = deptService;
    }
    
    
    /**
     * 部门-分页列表
     */
    @PostMapping("/list")
    public HttpResult pageDept(@RequestBody Page<Dept> page, Dept dept) {
        return HttpResult.success(deptService.pageDept(page, dept));
    }
    
    /**
     * 根据id获取部门详情
     */
    @GetMapping("/{id}")
    public HttpResult getDeptById(@PathVariable Long id) {
        return HttpResult.success(deptService.getDeptById(id));
    }
    
    /**
     * 新增-部门
     */
    @PostMapping
    public HttpResult insertDept(@RequestBody Dept dept) {
        deptService.insertDept(dept);
        return HttpResult.success();
    }
    
    /**
     * 修改-部门
     */
    @PutMapping
    public HttpResult updateDept(@RequestBody Dept dept) {
        deptService.updateDept(dept);
        return HttpResult.success();
    }
    
    /**
     * 删除-部门
     */
    @DeleteMapping("/{id}")
    public HttpResult deleteDept(@PathVariable("id") Long id) {
        deptService.deleteDept(id);
        return HttpResult.success();
    }
    
}
