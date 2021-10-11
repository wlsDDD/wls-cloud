package cn.erectpine.dict.api;

import cn.erectpine.common.core.constant.ServiceNameConstants;
import cn.erectpine.common.web.pojo.Result;
import cn.erectpine.dict.entity.DictData;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典数据API
 *
 * @author wls
 * @since 2021/9/3 14:47
 */
@Component
@FeignClient(value = ServiceNameConstants.WLS_DICT, path = "/" + ServiceNameConstants.WLS_DICT + "/dict-data")
public interface DictDataApi {
    
    /**
     * 字典数据-分页列表
     *
     * @param dictData dict类型数据
     * @return {@link Result}<{@link IPage}<{@link DictData}>>
     */
    @GetMapping("/page")
    Result<IPage<DictData>> pageDictData(DictData dictData);
    
    /**
     * 根据id获取字典数据详情
     *
     * @param id id
     * @return {@link Result}<{@link DictData}>
     */
    @GetMapping("/{id}")
    Result<DictData> getDictDataById(@PathVariable Long id);
    
    /**
     * 新增-字典数据
     *
     * @param dictData dict类型数据
     * @return {@link Result}<{@link ?}>
     */
    @PostMapping
    Result<?> insertDictData(@RequestBody @Validated DictData dictData);
    
    /**
     * 修改-字典数据
     *
     * @param dictData dict类型数据
     * @return {@link Result}<{@link ?}>
     */
    @PutMapping
    Result<?> updateDictData(@RequestBody @Validated DictData dictData);
    
    /**
     * 删除-字典数据
     *
     * @param ids id
     * @return {@link Result}<{@link ?}>
     */
    @DeleteMapping("/{ids}")
    Result<?> deleteDictData(@PathVariable("ids") List<Long> ids);
    
}
