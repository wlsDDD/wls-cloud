package ${package.Service};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * ${table.comment!}-列表
     *
     * @param page      分页参数
     * @param ${table.entityPath} 查询条件
     * @return 分页列表
     */
    IPage<${entity}> page${entity}(Page<${entity}> page, ${entity} ${table.entityPath});

    /**
     * 根据id获取${table.comment!}详情
     *
     * @param id id
     * @return {@link ${entity}}
     */
    ${entity} page${entity}(Long id);

    /**
     * 新增-${table.comment!}
     *
     * @param ${table.entityPath} ${table.comment!}
     */
    void insert${entity}(${entity} ${table.entityPath});

    /**
     * 修改-${table.comment!}
     *
     * @param ${table.entityPath} ${table.comment!}
     */
    void update${entity}(${entity} ${table.entityPath});

    /**
     * 删除-${table.comment!}
     *
     * @param id id
     */
    void delete${entity}(Long id);

}
</#if>
