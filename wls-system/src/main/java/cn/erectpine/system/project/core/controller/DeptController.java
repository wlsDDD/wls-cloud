package cn.erectpine.system.project.core.controller;

import cn.erectpine.common.web.ResponseTemplate;
import cn.erectpine.system.project.core.entity.Dept;
import cn.erectpine.system.project.core.service.IDeptService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/core/dept")
public class DeptController {
    
    @Autowired IDeptService deptService;
    
    
    /**
     * 部门-分页列表
     */
    @PostMapping("/list")
    public ResponseTemplate pageDept(@RequestBody Page<Dept> page, Dept dept) {
        return ResponseTemplate.success(deptService.pageDept(page, dept));
    }
    
    /**
     * 根据id获取部门详情
     */
    @GetMapping("/{id}")
    public ResponseTemplate getDeptById(@PathVariable Long id) {
        return ResponseTemplate.success(deptService.getDeptById(id));
    }
    
    /**
     * 新增-部门
     */
    @PostMapping
    public ResponseTemplate insertDept(@RequestBody Dept dept) {
        deptService.insertDept(dept);
        return ResponseTemplate.success();
    }
    
    /**
     * 修改-部门
     */
    @PutMapping
    public ResponseTemplate updateDept(@RequestBody Dept dept) {
        deptService.updateDept(dept);
        return ResponseTemplate.success();
    }
    
    /**
     * 删除-部门
     */
    @DeleteMapping("/{id}")
    public ResponseTemplate deleteDept(@PathVariable("id") Long id) {
        deptService.deleteDept(id);
        return ResponseTemplate.success();
    }
    
}
