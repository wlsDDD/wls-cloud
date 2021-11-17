package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ${cfg.BusinessException};
import ${cfg.CodeMsgEnum};
import ${cfg.PineAssert};
import ${cfg.PageUtil};
import java.util.List;

/**
 * <p>
 * ${table.comment?substring(0,table.comment?length-1)} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {
</#if>

    @Autowired private ${table.mapperName} ${table.entityPath}Mapper;

    /**
     * ${table.comment?substring(0,table.comment?length-1)}-列表
     *
     * @param ${table.entityPath} 查询条件
     * @return 分页列表
     */
    @Override
    public IPage<${entity}> page${entity}(${entity} ${table.entityPath}) {
        return page(PageUtil.getPlusPage(${table.entityPath}), Wrappers.lambdaQuery(${table.entityPath}));
    }

    /**
     * 根据id获取${table.comment!}详情
     *
     * @param id id
     * @return {@link ${entity}}
     */
    @Override
    public ${entity} get${entity}ById(Long id) {
        return getById(id);
    }

    /**
     * 新增-${table.comment?substring(0,table.comment?length-1)}
     *
     * @param ${table.entityPath} ${table.comment?substring(0,table.comment?length-1)}
     */
    @Override
    public void insert${entity}(${entity} ${table.entityPath}) {
        Asserts.isTrue(save(${table.entityPath}), () -> new BusinessException(CodeInfoEnum.DATA_INSERT_ERROR));
    }

    /**
     * 修改-${table.comment?substring(0,table.comment?length-1)}
     *
     * @param ${table.entityPath} ${table.comment?substring(0,table.comment?length-1)}
     */
    @Override
    public void update${entity}(${entity} ${table.entityPath}) {
        Asserts.isTrue(updateById(${table.entityPath}), () -> new BusinessException(CodeInfoEnum.DATA_UPDATE_ERROR));
    }

    /**
     * 删除-${table.comment?substring(0,table.comment?length-1)}
     *
     * @param ids ids
     */
    @Override
    public void delete${entity}(List<Long> ids) {
        Asserts.isTrue(removeByIds(ids), () -> new BusinessException(CodeInfoEnum.DATA_DELETE_ERROR));
    }

}
