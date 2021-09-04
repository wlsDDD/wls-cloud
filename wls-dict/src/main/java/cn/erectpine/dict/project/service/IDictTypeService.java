package cn.erectpine.dict.project.service;

import cn.erectpine.dict.entity.DictType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 字典类型 服务类
 * </p>
 *
 * @author wls
 * @since 2021-03-18
 */
public interface IDictTypeService extends IService<DictType> {
    
    /**
     * 字典类型-列表
     *
     * @param page     分页参数
     * @param dictType 查询条件
     * @return 分页列表
     */
    IPage<DictType> pageDictType(Page<DictType> page, DictType dictType);
    
    /**
     * 根据id获取字典类型表详情
     *
     * @param id id
     * @return {@link DictType}
     */
    DictType getDictTypeById(Long id);
    
    /**
     * 新增-字典类型
     *
     * @param dictType 字典类型
     */
    void insertDictType(DictType dictType);
    
    /**
     * 修改-字典类型
     *
     * @param dictType 字典类型
     */
    void updateDictType(DictType dictType);
    
    /**
     * 删除-字典类型
     *
     * @param id id
     */
    void deleteDictType(Long id);
    
}
