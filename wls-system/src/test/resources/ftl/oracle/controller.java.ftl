package ${package.Controller};


import io.swagger.annotations.Api;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hnct.active.framework.web.ResponseTemplate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
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

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Api(tags = "${table.comment!}")
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

    @Autowired
    ${table.serviceName} ${table.entityPath}Service;

    @ApiOperation("${table.comment!}-列表")
    @GetMapping
    public ResponseTemplate page${entity}(@ApiIgnore Page<${entity}> page, ${entity} ${table.entityPath}) {
        return ResponseTemplate.success(${table.entityPath}Service.page${entity}(page, ${table.entityPath}));
    }

    @ApiOperation("根据id获取${table.comment!}详情")
    @GetMapping("/{id}")
    public ResponseTemplate page${entity}(@PathVariable Long id) {
        return ResponseTemplate.success(${table.entityPath}Service.page${entity}(id));
    }

    @ApiOperation("新增-${table.comment!}")
    @PostMapping
    public ResponseTemplate insert${entity}(@RequestBody ${entity} ${table.entityPath}) {
        ${table.entityPath}Service.insert${entity}(${table.entityPath});
        return ResponseTemplate.success();
    }

    @ApiOperation("修改-${table.comment!}")
    @PutMapping
    public ResponseTemplate update${entity}(@RequestBody ${entity} ${table.entityPath}) {
        ${table.entityPath}Service.update${entity}(${table.entityPath});
        return ResponseTemplate.success();
    }

    @ApiOperation("删除-${table.comment!}")
    @DeleteMapping("/{id}")
    public ResponseTemplate delete${entity}(@PathVariable("id") Long id) {
        ${table.entityPath}Service.delete${entity}(id);
        return ResponseTemplate.success();
    }

}
</#if>
