package cn.wlsxl.dict.project.controller;

import cn.wlsxl.common.web.pojo.BaseController;
import cn.wlsxl.common.web.pojo.Result;
import cn.wlsxl.dict.api.DictDataApi;
import cn.wlsxl.dict.api.entity.DictData;
import cn.wlsxl.dict.project.service.IDictDataService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
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
public class DictDataController extends BaseController implements DictDataApi {
    
    @Autowired private IDictDataService dictDataService;
    
    
    /**
     * 页面dict类型数据
     *
     * @param dictData dict类型数据
     *
     * @return {@link Result }<{@link IPage }<{@link DictData }>>
     *
     * @author wls
     * @date 2022-07-18 15:11:34
     * @since 1.0.0
     */
    @GetMapping("/page")
    public Result<IPage<DictData>> pageDictData(DictData dictData) {
        return Result.ok(dictDataService.pageDictData(dictData)).page().tree();
    }
    
    /**
     * 通过id获取dict数据
     *
     * @param id id
     *
     * @return {@link Result }<{@link DictData }>
     *
     * @author wls
     * @date 2022-07-18 15:11:39
     * @since 1.0.0
     */
    public Result<DictData> getDictDataById(Long id) {
        return Result.ok(dictDataService.getDictDataById(id));
    }
    
    /**
     * 插入dict类型数据
     *
     * @param dictData dict类型数据
     *
     * @return {@link Result }<{@link ? }>
     *
     * @author wls
     * @date 2022-07-18 15:11:41
     * @since 1.0.0
     */
    public Result<?> insertDictData(DictData dictData) {
        dictDataService.insertDictData(dictData);
        return Result.ok();
    }
    
    /**
     * 更新dict类型数据
     *
     * @param dictData dict类型数据
     *
     * @return {@link Result }<{@link ? }>
     *
     * @author wls
     * @date 2022-07-18 15:11:43
     * @since 1.0.0
     */
    public Result<?> updateDictData(@RequestBody @Validated DictData dictData) {
        dictDataService.updateDictData(dictData);
        return Result.ok();
    }
    
    /**
     * 删除dict类型数据
     *
     * @param ids id
     *
     * @return {@link Result }<{@link ? }>
     *
     * @author wls
     * @date 2022-07-18 15:11:46
     * @since 1.0.0
     */
    public Result<?> deleteDictData(List<Long> ids) {
        dictDataService.deleteDictData(ids);
        return Result.ok();
    }
    
}
