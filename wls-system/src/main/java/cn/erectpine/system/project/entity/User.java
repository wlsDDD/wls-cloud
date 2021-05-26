package cn.erectpine.system.project.entity;

import cn.erectpine.common.gencode.md.WlsProperty;
import cn.erectpine.common.web.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author wls
 * @since 2021-01-20
 */
@Data
@WlsProperty("用户信息")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class User extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    @WlsProperty("用户ID")
    private Long userId;
    /**
     * 部门ID
     */
    @TableField("dept_id")
    @WlsProperty("部门ID")
    private Long deptId;
    /**
     * 用户账号
     */
    @TableField("user_name")
    @WlsProperty("用户账号")
    private String userName;
    /**
     * 用户昵称
     */
    @TableField("nick_name")
    @WlsProperty("用户昵称")
    private String nickName;
    /**
     * 用户类型（00系统用户）
     */
    @TableField("user_type")
    @WlsProperty("用户类型（00系统用户）")
    private String userType;
    /**
     * 用户邮箱
     */
    @TableField("email")
    @WlsProperty("用户邮箱")
    private String email;
    /**
     * 手机号码
     */
    @TableField("phonenumber")
    @WlsProperty("手机号码")
    private String phonenumber;
    /**
     * 用户性别（0男 1女 2未知）
     */
    @TableField("sex")
    @WlsProperty("用户性别（0男 1女 2未知）")
    private String sex;
    /**
     * 头像地址
     */
    @TableField("avatar")
    @WlsProperty("头像地址")
    private String avatar;
    /**
     * 密码
     */
    @TableField("password")
    @WlsProperty("密码")
    private String password;
    /**
     * 帐号状态（0正常 1停用）
     */
    @TableField("status")
    @WlsProperty("帐号状态（0正常 1停用）")
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableField("del_flag")
    @WlsProperty("删除标志（0代表存在 2代表删除")
    private String delFlag;
    /**
     * 最后登陆IP
     */
    @TableField("login_ip")
    @WlsProperty("最后登陆IP")
    private String loginIp;
    /**
     * 最后登陆时间
     */
    @TableField("login_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @WlsProperty("最后登陆时间")
    private LocalDateTime loginDate;
    /**
     * 备注
     */
    @TableField("remark")
    @WlsProperty("备注")
    private String remark;
    
}
