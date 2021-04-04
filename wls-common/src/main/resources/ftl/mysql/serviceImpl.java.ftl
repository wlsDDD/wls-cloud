package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import ${cfg.BusinessException};
import ${cfg.CodeMsgEnum};
import ${cfg.Assert};

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

    private final ${table.mapperName} ${table.entityPath}Mapper;

    public ${table.serviceImplName}(${table.mapperName} ${table.entityPath}Mapper) {
        this.${table.entityPath}Mapper = ${table.entityPath}Mapper;
    }


    /**
     * ${table.comment?substring(0,table.comment?length-1)}-列表
     *
     * @param page  分页参数
     * @param ${table.entityPath} 查询条件
     * @return 分页列表
     */
    @Override
    public IPage<${entity}> page${entity}(Page<${entity}> page, ${entity} ${table.entityPath}) {
        return page(page, Wrappers.lambdaQuery(${table.entityPath}));
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
        Assert.isTrue(save(${table.entityPath}), () -> new BusinessException(CodeMsgEnum.DATA_INSERT_ERROR));
    }

    /**
     * 修改-${table.comment?substring(0,table.comment?length-1)}
     *
     * @param ${table.entityPath} ${table.comment?substring(0,table.comment?length-1)}
     */
    @Override
    public void update${entity}(${entity} ${table.entityPath}) {
        Assert.isTrue(updateById(${table.entityPath}), () -> new BusinessException(CodeMsgEnum.DATA_UPDATE_ERROR));
    }

    /**
     * 删除-${table.comment?substring(0,table.comment?length-1)}
     *
     * @param id id
     */
    @Override
    public void delete${entity}(Long id) {
        Assert.isTrue(removeById(id), () -> new BusinessException(CodeMsgEnum.DATA_DELETE_ERROR));
    }

}
