package plus.wls.system.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import plus.wls.common.web.pojo.BaseEntity;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author wls
 * @since 2021-11-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户ID
     * bigint
     */
    // @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
    /**
     * 部门ID
     * bigint
     */
    private Long deptId;
    /**
     * 用户账号
     * varchar(30)
     */
    @NotBlank
    private String userName;
    /**
     * 用户昵称
     * varchar(30)
     */
    @NotBlank
    private String nickName;
    /**
     * 用户类型（00系统用户）
     * varchar(2)
     */
    private String userType;
    /**
     * 用户邮箱
     * varchar(50)
     */
    private String email;
    /**
     * 手机号码
     * varchar(11)
     */
    private String phonenumber;
    /**
     * 用户性别（0男 1女 2未知）
     * char(1)
     */
    private String sex;
    /**
     * 头像地址
     * varchar(100)
     */
    private String avatar;
    /**
     * 密码
     * varchar(100)
     */
    private String password;
    /**
     * 帐号状态（0正常 1停用）
     * char(1)
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     * char(1)
     */
    private String delFlag;
    /**
     * 最后登录IP
     * varchar(50)
     */
    private String loginIp;
    /**
     * 最后登录时间
     * datetime
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginDate;
    /**
     * 备注
     * varchar(500)
     */
    private String remark;
    
}
