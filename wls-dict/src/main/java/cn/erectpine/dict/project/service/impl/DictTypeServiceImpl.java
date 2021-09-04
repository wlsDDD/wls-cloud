package cn.erectpine.dict.project.service.impl;

import cn.erectpine.dict.entity.DictType;
import cn.erectpine.dict.project.mapper.DictTypeMapper;
import cn.erectpine.dict.project.service.IDictTypeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典类型 服务实现类
 * </p>
 *
 * @author wls
 * @since 2021-03-18
 */
@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements IDictTypeService {
    
    @Autowired DictTypeMapper dictTypeMapper;
    
    
    /**
     * 字典类型-列表
     *
     * @param page     分页参数
     * @param dictType 查询条件
     * @return 分页列表
     */
    @Override
    public IPage<DictType> pageDictType(Page<DictType> page, DictType dictType) {
        return page(page, Wrappers.lambdaQuery(dictType));
    }
    
    /**
     * 根据id获取字典类型表详情
     *
     * @param id id
     * @return {@link DictType}
     */
    @Override
    public DictType getDictTypeById(Long id) {
        return getById(id);
    }
    
    /**
     * 新增-字典类型
     *
     * @param dictType 字典类型
     */
    @Override
    public void insertDictType(DictType dictType) {
        save(dictType);
    }
    
    /**
     * 修改-字典类型
     *
     * @param dictType 字典类型
     */
    @Override
    public void updateDictType(DictType dictType) {
        updateById(dictType);
    }
    
    /**
     * 删除-字典类型
     *
     * @param id id
     */
    @Override
    public void deleteDictType(Long id) {
        removeById(id);
    }
    
}
