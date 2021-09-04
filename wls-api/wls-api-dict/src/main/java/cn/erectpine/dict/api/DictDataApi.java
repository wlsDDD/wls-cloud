package cn.erectpine.dict.api;

import cn.erectpine.common.core.constant.ServiceNameConstants;
import cn.erectpine.common.web.pojo.HttpResult;
import cn.erectpine.dict.entity.DictData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * 字典数据API
 *
 * @Author wls
 * @Date 2021/9/3 14:47
 */
@Component
@FeignClient(ServiceNameConstants.WLS_DICT)
public interface DictDataApi {
    
    /**
     * 字典数据-分页列表
     *
     * @param page     页面
     * @param dictData dict类型数据
     * @return {@link HttpResult}
     */
    @PostMapping("/list")
    HttpResult pageDictData(DictData dictData);
    
    /**
     * 根据id获取字典数据详情
     *
     * @param id id
     * @return {@link HttpResult}
     */
    @GetMapping("/{id}")
    HttpResult getDictDataById(@PathVariable("id") Long id);
    
    /**
     * 新增-字典数据
     *
     * @param dictData dict类型数据
     * @return {@link HttpResult}
     */
    @PostMapping
    HttpResult insertDictData(@RequestBody DictData dictData);
    
    /**
     * 修改-字典数据
     *
     * @param dictData dict类型数据
     * @return {@link HttpResult}
     */
    @PutMapping
    HttpResult updateDictData(@RequestBody DictData dictData);
    
    /**
     * 删除-字典数据
     *
     * @param id id
     * @return {@link HttpResult}
     */
    @DeleteMapping("/{id}")
    HttpResult deleteDictData(@PathVariable("id") Long id);
    
}
