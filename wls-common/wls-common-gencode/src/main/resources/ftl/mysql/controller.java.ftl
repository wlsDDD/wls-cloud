package ${package.Controller};

<#if swagger2>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
<#else>
</#if>
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${cfg.Result};
import java.util.List;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment?substring(0,table.comment?length-1)} 控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if swagger2>
@Api(tags = "${table.comment?substring(0,table.comment?length-1)}")
<#else>
</#if>
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired private ${table.serviceName} ${table.entityPath}Service;


<#if !swagger2>
    /**
     * ${table.comment?substring(0,table.comment?length-1)}-分页列表
     */
<#else>
    @ApiOperation("${table.comment?substring(0,table.comment?length-1)}-分页列表")
</#if>
    @GetMapping("/page")
    public Result<IPage<${entity}>> page${entity}(${entity} ${table.entityPath}) {
        return Result.ok(${table.entityPath}Service.page${entity}(${table.entityPath}));
    }

<#if !swagger2>
    /**
     * 根据id获取${table.comment?substring(0,table.comment?length-1)}详情
     */
<#else>
    @ApiOperation("根据id获取${table.comment?substring(0,table.comment?length-1)}详情")
</#if>
    @GetMapping("/{id}")
    public Result<${entity}> get${entity}ById(@PathVariable("id") Long id) {
        return Result.ok(${table.entityPath}Service.get${entity}ById(id));
    }

<#if !swagger2>
    /**
     * 新增-${table.comment?substring(0,table.comment?length-1)}
     */
<#else>
    @ApiOperation("新增-${table.comment?substring(0,table.comment?length-1)}")
</#if>
    @PostMapping
    public Result<?> insert${entity}(@RequestBody @Validated ${entity} ${table.entityPath}) {
        ${table.entityPath}Service.insert${entity}(${table.entityPath});
        return Result.ok();
    }

<#if !swagger2>
    /**
     * 修改-${table.comment?substring(0,table.comment?length-1)}
     */
<#else>
    @ApiOperation("修改-${table.comment?substring(0,table.comment?length-1)}")
</#if>
    @PutMapping
    public Result<?> update${entity}(@RequestBody @Validated ${entity} ${table.entityPath}) {
        ${table.entityPath}Service.update${entity}(${table.entityPath});
        return Result.ok();
    }

<#if !swagger2>
    /**
     * 删除-${table.comment?substring(0,table.comment?length-1)}
     */
<#else>
    @ApiOperation("删除-${table.comment?substring(0,table.comment?length-1)}")
</#if>
    @DeleteMapping("/{ids}")
    public Result<?> delete${entity}(@PathVariable("ids") List<Long> ids) {
        ${table.entityPath}Service.delete${entity}(ids);
        return Result.ok();
    }

}
</#if>
