package cn.erectpine.dict.project.controller;

import cn.erectpine.common.web.pojo.BaseController;
import cn.erectpine.dict.entity.DictData;
import cn.erectpine.dict.project.service.IDictDataService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典数据 控制器
 * </p>
 *
 * @author wls
 * @since 2021-09-05
 */
@RestController
@RequestMapping("/dict-data")
public class DictDataController extends BaseController {
    
    @Autowired private IDictDataService dictDataService;
    
    
    /**
     * 字典数据-分页列表
     */
    @PostMapping("/page")
    public IPage<DictData> pageDictData(@RequestBody Page<DictData> page, DictData dictData) {
        return dictDataService.pageDictData(page, dictData);
    }
    
    /**
     * 根据id获取字典数据详情
     */
    @GetMapping("/{id}")
    public DictData getDictDataById(@PathVariable Long id) {
        return dictDataService.getDictDataById(id);
    }
    
    /**
     * 新增-字典数据
     */
    @PostMapping
    public void insertDictData(@RequestBody DictData dictData) {
        dictDataService.insertDictData(dictData);
    }
    
    /**
     * 修改-字典数据
     */
    @PutMapping
    public void updateDictData(@RequestBody DictData dictData) {
        dictDataService.updateDictData(dictData);
    }
    
    /**
     * 删除-字典数据
     */
    @DeleteMapping("/{id}")
    public void deleteDictData(@PathVariable("id") Long id) {
        dictDataService.deleteDictData(id);
    }
    
}
