package cn.erectpine.system.project.entity;

import cn.erectpine.common.web.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户与岗位关联
 * </p>
 *
 * @author wls
 * @since 2021-04-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user_post")
public class UserPost extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
    
    /**
     * 岗位ID
     */
    private Long postId;
    
    
}
