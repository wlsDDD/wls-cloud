package cn.erectpine.dict.project.controller;

import cn.erectpine.common.web.pojo.BaseController;
import cn.erectpine.common.web.pojo.Result;
import cn.erectpine.dict.project.entity.DictData;
import cn.erectpine.dict.project.service.IDictDataService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 字典数据 控制器
 * </p>
 *
 * @author wls
 * @since 2021-09-22
 */
@Api(tags = "字典数据")
@RestController
@RequestMapping("/dict-data")
public class DictDataController extends BaseController {
    
    @Autowired private IDictDataService dictDataService;
    
    
    @ApiOperation("字典数据-分页列表")
    @GetMapping("/page")
    public Result<IPage<DictData>> pageDictData(DictData dictData) {
        return Result.ok(dictDataService.pageDictData(dictData)).page().tree();
    }
    
    @ApiOperation("根据id获取字典数据详情")
    @GetMapping("/{id}")
    public Result<DictData> getDictDataById(@PathVariable Long id) {
        return Result.ok(dictDataService.getDictDataById(id));
    }
    
    @ApiOperation("新增-字典数据")
    @PostMapping
    public Result<?> insertDictData(@RequestBody @Validated DictData dictData) {
        dictDataService.insertDictData(dictData);
        return Result.ok();
    }
    
    @ApiOperation("修改-字典数据")
    @PutMapping
    public Result<?> updateDictData(@RequestBody @Validated DictData dictData) {
        dictDataService.updateDictData(dictData);
        return Result.ok();
    }
    
    @ApiOperation("删除-字典数据")
    @DeleteMapping("/{ids}")
    public Result<?> deleteDictData(@PathVariable("ids") List<Long> ids) {
        dictDataService.deleteDictData(ids);
        return Result.ok();
    }
    
}
