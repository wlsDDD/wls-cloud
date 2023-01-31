package plus.wls.dict.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import plus.wls.dict.api.entity.DictData;

import java.util.List;

/**
 * <p>
 * 字典数据 服务类
 * </p>
 *
 * @author wls
 * @since 2021-09-22
 */
public interface IDictDataService extends IService<DictData> {
    
    /**
     * 字典数据-列表
     *
     * @param dictData 查询条件
     *
     * @return 分页列表
     */
    IPage<DictData> pageDictData(DictData dictData);
    
    /**
     * 根据id获取字典数据表详情
     *
     * @param id id
     *
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
     * @param ids ids
     */
    void deleteDictData(List<Long> ids);
    
}
