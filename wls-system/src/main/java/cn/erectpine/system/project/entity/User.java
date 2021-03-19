package cn.erectpine.system.project.entity;

import cn.erectpine.common.web.BaseEntity;
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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class User extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
    /**
     * 部门ID
     */
    @TableField("dept_id")
    private Long deptId;
    /**
     * 用户账号
     */
    @TableField("user_name")
    private String userName;
    /**
     * 用户昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 用户类型（00系统用户）
     */
    @TableField("user_type")
    private String userType;
    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 手机号码
     */
    @TableField("phonenumber")
    private String phonenumber;
    /**
     * 用户性别（0男 1女 2未知）
     */
    @TableField("sex")
    private String sex;
    /**
     * 头像地址
     */
    @TableField("avatar")
    private String avatar;
    /**
     * 密码
     */
    @TableField("password")
    private String password;
    /**
     * 帐号状态（0正常 1停用）
     */
    @TableField("status")
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableField("del_flag")
    private String delFlag;
    /**
     * 最后登陆IP
     */
    @TableField("login_ip")
    private String loginIp;
    /**
     * 最后登陆时间
     */
    @TableField("login_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginDate;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    
}
