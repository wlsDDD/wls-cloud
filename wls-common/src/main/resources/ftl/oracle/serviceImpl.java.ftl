package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * ${table.comment!} 服务实现类
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

    @Autowired
    ${table.mapperName} ${table.entityPath}Mapper;

    /**
     * ${table.comment!}-列表
     *
     * @param page  分页参数
     * @param ${table.entityPath} 查询条件
     * @return 分页列表
     */
    @Override
    public IPage<${entity}> page${entity}(Page<${entity}> page, ${entity} ${table.entityPath}) {
        return page(page, Wrappers.lambdaQuery(${table.entityPath}).orderByDesc(${entity}::getCreateTime));
    }

    /**
     * 根据id获取${table.comment!}详情
     *
     * @param id id
     * @return {@link ${entity}}
     */
    @Override
    public ${entity} page${entity}(Long id) {
        return getById(id);
    }

    /**
     * 新增-${table.comment!}
     *
     * @param ${table.entityPath} ${table.comment!}
     */
    @Override
    public void insert${entity}(${entity} ${table.entityPath}) {
        save(${table.entityPath});
    }

    /**
     * 修改-${table.comment!}
     *
     * @param ${table.entityPath} ${table.comment!}
     */
    @Override
    public void update${entity}(${entity} ${table.entityPath}) {
        updateById(${table.entityPath});
    }

    /**
     * 删除-${table.comment!}
     *
     * @param id id
     */
    @Override
    public void delete${entity}(Long id) {
        removeById(id);
    }

}
