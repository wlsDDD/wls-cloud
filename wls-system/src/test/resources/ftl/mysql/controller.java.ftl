package ${package.Controller};

<#if swagger2>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;
<#else>
</#if>
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import ${cfg.ResponseTemplate};

/**
 * <p>
 * ${table.comment?substring(0,table.comment?length-1)} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if swagger2>
@ApiModelProperty(value = "${field.comment}")
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

    @Autowired ${table.serviceName} ${table.entityPath}Service;


    <#if !swagger2>
    /**
     * ${table.comment?substring(0,table.comment?length-1)}-分页列表
     */
    <#else>
    @ApiOperation("${table.comment?substring(0,table.comment?length-1)}-分页列表")
    </#if>
    @PostMapping("/list")
    public ResponseTemplate page${entity}(@RequestBody <#if swagger2>@ApiIgnore </#if>Page<${entity}> page, ${entity} ${table.entityPath}) {
        return ResponseTemplate.success(${table.entityPath}Service.page${entity}(page, ${table.entityPath}));
    }

    <#if !swagger2>
    /**
     * 根据id获取${table.comment?substring(0,table.comment?length-1)}详情
     */
    <#else>
    @ApiOperation("根据id获取${table.comment?substring(0,table.comment?length-1)}详情")
    </#if>
    @GetMapping("/{id}")
    public ResponseTemplate get${entity}ById(@PathVariable Long id) {
        return ResponseTemplate.success(${table.entityPath}Service.get${entity}ById(id));
    }

    <#if !swagger2>
    /**
     * 新增-${table.comment?substring(0,table.comment?length-1)}
     */
    <#else>
    @ApiOperation("新增-${table.comment?substring(0,table.comment?length-1)}")
    </#if>
    @PostMapping
    public ResponseTemplate insert${entity}(@RequestBody ${entity} ${table.entityPath}) {
        ${table.entityPath}Service.insert${entity}(${table.entityPath});
        return ResponseTemplate.success();
    }

    <#if !swagger2>
    /**
     * 修改-${table.comment?substring(0,table.comment?length-1)}
     */
    <#else>
    @ApiOperation("修改-${table.comment?substring(0,table.comment?length-1)}")
    </#if>
    @PutMapping
    public ResponseTemplate update${entity}(@RequestBody ${entity} ${table.entityPath}) {
        ${table.entityPath}Service.update${entity}(${table.entityPath});
        return ResponseTemplate.success();
    }

    <#if !swagger2>
    /**
     * 删除-${table.comment?substring(0,table.comment?length-1)}
     */
    <#else>
    @ApiOperation("删除-${table.comment?substring(0,table.comment?length-1)}")
    </#if>
    @DeleteMapping("/{id}")
    public ResponseTemplate delete${entity}(@PathVariable("id") Long id) {
        ${table.entityPath}Service.delete${entity}(id);
        return ResponseTemplate.success();
    }

}
</#if>
