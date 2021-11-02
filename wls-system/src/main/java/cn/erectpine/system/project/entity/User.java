package cn.erectpine.system.project.entity;

import cn.erectpine.common.core.enums.StatusEnum;
import cn.erectpine.common.web.annotation.EnumValid;
import cn.erectpine.common.web.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author wls
 * @since 2021-10-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "User对象", description = "用户信息")
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     * bigint
     */
    @ApiModelProperty(value = "用户ID")
    @TableId(value = "user_id", type = IdType.AUTO)
    @EnumValid(StatusEnum.class)
    private Long userId;
    /**
     * 部门ID
     * bigint
     */
    @ApiModelProperty(value = "部门ID")
    private Long deptId;
    /**
     * 用户账号
     * varchar(30)
     */
    @NotBlank
    @ApiModelProperty(value = "用户账号")
    private String userName;
    /**
     * 用户昵称
     * varchar(30)
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    /**
     * 用户类型（00系统用户）
     * varchar(2)
     */
    @ApiModelProperty(value = "用户类型（00系统用户）")
    private String userType;
    /**
     * 用户邮箱
     * varchar(50)
     */
    @ApiModelProperty(value = "用户邮箱")
    private String email;
    /**
     * 手机号码
     * varchar(11)
     */
    @ApiModelProperty(value = "手机号码")
    private String phonenumber;
    /**
     * 用户性别（0男 1女 2未知）
     * char(1)
     */
    @ApiModelProperty(value = "用户性别（0男 1女 2未知）")
    private String sex;
    /**
     * 头像地址
     * varchar(100)
     */
    @ApiModelProperty(value = "头像地址")
    private String avatar;
    /**
     * 密码
     * varchar(100)
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 帐号状态（0正常 1停用）
     * char(1)
     */
    @ApiModelProperty(value = "帐号状态（0正常 1停用）")
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     * char(1)
     */
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;
    /**
     * 最后登录IP
     * varchar(50)
     */
    @ApiModelProperty(value = "最后登录IP")
    private String loginIp;
    /**
     * 最后登录时间
     * datetime
     */
    @ApiModelProperty(value = "最后登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginDate;
    /**
     * 备注
     * varchar(500)
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
