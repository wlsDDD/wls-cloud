package cn.erectpine.dict.project.service;

import cn.erectpine.dict.entity.DictData;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 字典数据 服务类
 * </p>
 *
 * @author wls
 * @since 2021-03-18
 */
public interface IDictDataService extends IService<DictData> {
    
    /**
     * 字典数据-列表
     *
     * @param page     分页参数
     * @param dictData 查询条件
     * @return 分页列表
     */
    IPage<DictData> pageDictData(Page<DictData> page, DictData dictData);
    
    /**
     * 根据id获取字典数据表详情
     *
     * @param id id
     * @return {@link DictData}
     */
    DictData getDictDataById(Long id);
    
    /**
     * 新增-字典数据
     *
     * @param dictData 字典数据
     */
    void insertDictData(DictData dictData);
    
    /**
     * 修改-字典数据
     *
     * @param dictData 字典数据
     */
    void updateDictData(DictData dictData);
    
    /**
     * 删除-字典数据
     *
     * @param id id
     */
    void deleteDictData(Long id);
    
}
