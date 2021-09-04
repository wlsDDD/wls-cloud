package cn.erectpine.dict.project.service.impl;

import cn.erectpine.dict.entity.DictData;
import cn.erectpine.dict.project.mapper.DictDataMapper;
import cn.erectpine.dict.project.service.IDictDataService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典数据 服务实现类
 * </p>
 *
 * @author wls
 * @since 2021-03-18
 */
@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements IDictDataService {
    
    @Autowired DictDataMapper dictDataMapper;
    
    
    /**
     * 字典数据-列表
     *
     * @param page     分页参数
     * @param dictData 查询条件
     * @return 分页列表
     */
    @Override
    public IPage<DictData> pageDictData(Page<DictData> page, DictData dictData) {
        return page(page, Wrappers.lambdaQuery(dictData));
    }
    
    /**
     * 根据id获取字典数据表详情
     *
     * @param id id
     * @return {@link DictData}
     */
    @Override
    public DictData getDictDataById(Long id) {
        return getById(id);
    }
    
    /**
     * 新增-字典数据
     *
     * @param dictData 字典数据
     */
    @Override
    public void insertDictData(DictData dictData) {
        save(dictData);
    }
    
    /**
     * 修改-字典数据
     *
     * @param dictData 字典数据
     */
    @Override
    public void updateDictData(DictData dictData) {
        updateById(dictData);
    }
    
    /**
     * 删除-字典数据
     *
     * @param id id
     */
    @Override
    public void deleteDictData(Long id) {
        removeById(id);
    }
    
}
