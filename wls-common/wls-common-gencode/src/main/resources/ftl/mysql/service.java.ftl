package ${package.Service};

import com.baomidou.mybatisplus.core.metadata.IPage;
import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import java.util.List;

/**
 * <p>
 * ${table.comment?substring(0,table.comment?length-1)} 服务类
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
     * ${table.comment?substring(0,table.comment?length-1)}-列表
     *
     * @param ${table.entityPath} 查询条件
     * @return 分页列表
     */
    IPage<${entity}> page${entity}(${entity} ${table.entityPath});

    /**
     * 根据id获取${table.comment!}详情
     *
     * @param id id
     * @return {@link ${entity}}
     */
    ${entity} get${entity}ById(Long id);

    /**
     * 新增-${table.comment?substring(0,table.comment?length-1)}
     *
     * @param ${table.entityPath} ${table.comment?substring(0,table.comment?length-1)}
     */
    void insert${entity}(${entity} ${table.entityPath});

    /**
     * 修改-${table.comment?substring(0,table.comment?length-1)}
     *
     * @param ${table.entityPath} ${table.comment?substring(0,table.comment?length-1)}
     */
    void update${entity}(${entity} ${table.entityPath});

    /**
     * 删除-${table.comment?substring(0,table.comment?length-1)}
     *
     * @param ids ids
     */
    void delete${entity}(List<Long> ids);

}
</#if>
