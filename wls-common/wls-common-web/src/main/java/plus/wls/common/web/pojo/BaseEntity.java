package plus.wls.common.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体类基类
 *
 * @author wls
 * @since 2021/1/13 9:32
 */
@Data
public abstract class BaseEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID 自增策略
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 租户ID
     */
    @JsonIgnore
    private String tenantId;
    
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    
    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    
    /**
     * 逻辑删除字段 0未删除 其他已删除 已删除时存ID值
     */
    @JsonIgnore
    @TableField("is_deleted")
    private Long deleted;
    
}
