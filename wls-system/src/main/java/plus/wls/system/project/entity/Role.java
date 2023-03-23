package plus.wls.system.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import plus.wls.common.web.pojo.BaseEntity;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 角色信息
 * </p>
 *
 * @author wls
 * @since 2021-11-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
public class Role extends BaseEntity {
    private static final long serialVersionUID = 1L;
    
    /**
     * 角色ID
     * bigint
     */
    // @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;
    /**
     * 角色名称
     * varchar(30)
     */
    private String roleName;
    /**
     * 角色权限字符串
     * varchar(100)
     */
    @NotBlank
    private String roleKey;
    /**
     * 显示顺序
     * int
     */
    private Integer roleSort;
    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     * char(1)
     */
    private String dataScope;
    /**
     * 菜单树选择项是否关联显示
     * tinyint(1)
     */
    private Boolean menuCheckStrictly;
    /**
     * 部门树选择项是否关联显示
     * tinyint(1)
     */
    private Boolean deptCheckStrictly;
    /**
     * 角色状态（0正常 1停用）
     * char(1)
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     * char(1)
     */
    private String delFlag;
    /**
     * 备注
     * varchar(500)
     */
    private String remark;
    
}
