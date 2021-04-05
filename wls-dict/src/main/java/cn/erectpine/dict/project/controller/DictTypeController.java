package cn.erectpine.dict.project.controller;

import cn.erectpine.common.web.HttpResult;
import cn.erectpine.dict.project.entity.DictType;
import cn.erectpine.dict.project.service.IDictTypeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典类型 前端控制器
 * </p>
 *
 * @author wls
 * @since 2021-03-18
 */
@RestController
@RequestMapping("/dict-type")
public class DictTypeController {
    
    @Autowired IDictTypeService dictTypeService;
    
    
    /**
     * 字典类型-分页列表
     */
    @PostMapping("/list")
    public HttpResult pageDictType(@RequestBody Page<DictType> page, DictType dictType) {
        return HttpResult.success(dictTypeService.pageDictType(page, dictType));
    }
    
    /**
     * 根据id获取字典类型详情
     */
    @GetMapping("/{id}")
    public HttpResult getDictTypeById(@PathVariable Long id) {
        return HttpResult.success(dictTypeService.getDictTypeById(id));
    }
    
    /**
     * 新增-字典类型
     */
    @PostMapping
    public HttpResult insertDictType(@RequestBody DictType dictType) {
        dictTypeService.insertDictType(dictType);
        return HttpResult.success();
    }
    
    /**
     * 修改-字典类型
     */
    @PutMapping
    public HttpResult updateDictType(@RequestBody DictType dictType) {
        dictTypeService.updateDictType(dictType);
        return HttpResult.success();
    }
    
    /**
     * 删除-字典类型
     */
    @DeleteMapping("/{id}")
    public HttpResult deleteDictType(@PathVariable("id") Long id) {
        dictTypeService.deleteDictType(id);
        return HttpResult.success();
    }
    
}
