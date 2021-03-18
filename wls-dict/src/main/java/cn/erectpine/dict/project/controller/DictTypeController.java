package cn.erectpine.dict.project.controller;

import cn.erectpine.common.web.ResponseTemplate;
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
    public ResponseTemplate pageDictType(@RequestBody Page<DictType> page, DictType dictType) {
        return ResponseTemplate.success(dictTypeService.pageDictType(page, dictType));
    }
    
    /**
     * 根据id获取字典类型详情
     */
    @GetMapping("/{id}")
    public ResponseTemplate getDictTypeById(@PathVariable Long id) {
        return ResponseTemplate.success(dictTypeService.getDictTypeById(id));
    }
    
    /**
     * 新增-字典类型
     */
    @PostMapping
    public ResponseTemplate insertDictType(@RequestBody DictType dictType) {
        dictTypeService.insertDictType(dictType);
        return ResponseTemplate.success();
    }
    
    /**
     * 修改-字典类型
     */
    @PutMapping
    public ResponseTemplate updateDictType(@RequestBody DictType dictType) {
        dictTypeService.updateDictType(dictType);
        return ResponseTemplate.success();
    }
    
    /**
     * 删除-字典类型
     */
    @DeleteMapping("/{id}")
    public ResponseTemplate deleteDictType(@PathVariable("id") Long id) {
        dictTypeService.deleteDictType(id);
        return ResponseTemplate.success();
    }
    
}
