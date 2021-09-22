package cn.erectpine.dict.api;

import cn.erectpine.common.core.constant.ServiceNameConstants;
import cn.erectpine.common.web.pojo.Result;
import cn.erectpine.dict.entity.DictData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

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
     * @param page     页面
     * @param dictData dict类型数据
     * @return {@link Result}
     */
    @PostMapping("/page")
    Result pageDictData(DictData dictData);
    
    /**
     * 根据id获取字典数据详情
     *
     * @param id id
     * @return {@link Result}
     */
    @GetMapping("/{id}")
    Result getDictDataById(@PathVariable("id") Long id);
    
    /**
     * 新增-字典数据
     *
     * @param dictData dict类型数据
     * @return {@link Result}
     */
    @PostMapping
    Result insertDictData(@RequestBody DictData dictData);
    
    /**
     * 修改-字典数据
     *
     * @param dictData dict类型数据
     * @return {@link Result}
     */
    @PutMapping
    Result updateDictData(@RequestBody DictData dictData);
    
    /**
     * 删除-字典数据
     *
     * @param id id
     * @return {@link Result}
     */
    @DeleteMapping("/{id}")
    Result deleteDictData(@PathVariable("id") Long id);
    
}
