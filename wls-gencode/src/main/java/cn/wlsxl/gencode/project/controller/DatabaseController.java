package cn.wlsxl.gencode.project.controller;


import cn.wlsxl.common.web.pojo.Result;
import cn.wlsxl.gencode.project.entity.Database;
import cn.wlsxl.gencode.project.service.IDatabaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 数据库连接配置表 前端控制器
 * </p>
 *
 * @author wls
 * @since 2022-07-18
 */
@Controller
@RequestMapping("/database")
public class DatabaseController {
    
    @Autowired private IDatabaseService databaseService;
    
    /**
     * 分页列表-数据库连接配置表
     */
    @GetMapping("/page")
    public Result<IPage<Database>> pageDatabase(Database database) {
        return Result.ok(databaseService.pageDatabase(database));
    }
    
    /**
     * 根据id获取数据库连接配置表详情
     */
    @GetMapping("/{id}")
    public Result<Database> getDatabaseById(@PathVariable("id") Long id) {
        return Result.ok(databaseService.getDatabaseById(id));
    }
    
    /**
     * 新增-数据库连接配置表
     */
    @PostMapping
    public Result<?> insertDatabase(@RequestBody @Validated Database database) {
        databaseService.insertDatabase(database);
        return Result.ok();
    }
    
    /**
     * 修改-数据库连接配置表
     */
    @PutMapping
    public Result<?> updateDatabase(@RequestBody @Validated Database database) {
        databaseService.updateDatabase(database);
        return Result.ok();
    }
    
    /**
     * 删除-数据库连接配置表
     */
    @DeleteMapping("/{ids}")
    public Result<?> deleteDatabase(@PathVariable("ids") List<Long> ids) {
        databaseService.deleteDatabase(ids);
        return Result.ok();
    }
    
}
