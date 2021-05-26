package cn.erectpine.dict.project.controller;

import cn.erectpine.common.web.pojo.BaseController;
import cn.erectpine.common.web.pojo.HttpResult;
import cn.erectpine.dict.project.entity.DictData;
import cn.erectpine.dict.project.service.IDictDataService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典数据 前端控制器
 * </p>
 *
 * @author wls
 * @since 2021-03-18
 */
@RestController
@RequestMapping("/dict-data")
public class DictDataController extends BaseController {
    
    @Autowired IDictDataService dictDataService;
    
    
    /**
     * 字典数据-分页列表
     */
    @PostMapping("/list")
    public HttpResult pageDictData(@RequestBody Page<DictData> page, DictData dictData) {
        return HttpResult.success(dictDataService.pageDictData(page, dictData));
    }
    
    /**
     * 根据id获取字典数据详情
     */
    @GetMapping("/{id}")
    public HttpResult getDictDataById(@PathVariable Long id) {
        return HttpResult.success(dictDataService.getDictDataById(id));
    }
    
    /**
     * 新增-字典数据
     */
    @PostMapping
    public HttpResult insertDictData(@RequestBody DictData dictData) {
        dictDataService.insertDictData(dictData);
        return HttpResult.success();
    }
    
    /**
     * 修改-字典数据
     */
    @PutMapping
    public HttpResult updateDictData(@RequestBody DictData dictData) {
        dictDataService.updateDictData(dictData);
        return HttpResult.success();
    }
    
    /**
     * 删除-字典数据
     */
    @DeleteMapping("/{id}")
    public HttpResult deleteDictData(@PathVariable("id") Long id) {
        dictDataService.deleteDictData(id);
        return HttpResult.success();
    }
    
}
