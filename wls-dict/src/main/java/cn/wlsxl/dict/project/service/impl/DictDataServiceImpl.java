package cn.wlsxl.dict.project.service.impl;

import cn.wlsxl.common.core.enums.CodeInfoEnum;
import cn.wlsxl.common.core.exception.BusinessException;
import cn.wlsxl.common.core.util.Asserts;
import cn.wlsxl.common.web.util.PageUtil;
import cn.wlsxl.dict.api.entity.DictData;
import cn.wlsxl.dict.project.mapper.DictDataMapper;
import cn.wlsxl.dict.project.service.IDictDataService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典数据 服务实现类
 * </p>
 *
 * @author wls
 * @since 2021-09-22
 */
@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements IDictDataService {
    
    @Autowired private DictDataMapper dictDataMapper;
    
    /**
     * 字典数据-列表
     *
     * @param dictData 查询条件
     *
     * @return 分页列表
     */
    @Override
    public IPage<DictData> pageDictData(DictData dictData) {
        return page(PageUtil.getPlusPage(dictData), Wrappers.lambdaQuery(dictData));
    }
    
    /**
     * 根据id获取字典数据表详情
     *
     * @param id id
     *
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
        Asserts.isTrue(save(dictData), () -> new BusinessException(CodeInfoEnum.DATA_INSERT_ERROR));
    }
    
    /**
     * 修改-字典数据
     *
     * @param dictData 字典数据
     */
    @Override
    public void updateDictData(DictData dictData) {
        Asserts.isTrue(updateById(dictData), () -> new BusinessException(CodeInfoEnum.DATA_UPDATE_ERROR));
    }
    
    /**
     * 删除-字典数据
     *
     * @param ids ids
     */
    @Override
    public void deleteDictData(List<Long> ids) {
        Asserts.isTrue(removeByIds(ids), () -> new BusinessException(CodeInfoEnum.DATA_DELETE_ERROR));
    }
    
}
