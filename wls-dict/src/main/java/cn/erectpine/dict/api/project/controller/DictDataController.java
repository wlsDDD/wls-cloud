package cn.erectpine.dict.api.project.controller;

import cn.erectpine.common.web.pojo.BaseController;
import cn.erectpine.common.web.pojo.Result;
import cn.erectpine.dict.api.DictDataApi;
import cn.erectpine.dict.api.entity.DictData;
import cn.erectpine.dict.api.project.service.IDictDataService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class DictDataController extends BaseController implements DictDataApi {
    
    @Autowired private IDictDataService dictDataService;
    
    
    public Result<IPage<DictData>> pageDictData(DictData dictData) {
        return Result.ok(dictDataService.pageDictData(dictData)).page().tree();
    }
    
    public Result<DictData> getDictDataById(@PathVariable Long id) {
        return Result.ok(dictDataService.getDictDataById(id));
    }
    
    public Result<?> insertDictData(@RequestBody @Validated DictData dictData) {
        dictDataService.insertDictData(dictData);
        return Result.ok();
    }
    
    public Result<?> updateDictData(@RequestBody @Validated DictData dictData) {
        dictDataService.updateDictData(dictData);
        return Result.ok();
    }
    
    public Result<?> deleteDictData(@PathVariable("ids") List<Long> ids) {
        dictDataService.deleteDictData(ids);
        return Result.ok();
    }
    
}
