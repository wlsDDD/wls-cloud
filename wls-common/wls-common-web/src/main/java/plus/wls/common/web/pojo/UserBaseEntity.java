package plus.wls.common.web.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 带用户的实体类基类
 *
 * @author wls
 * @date 2022-07-19 11:48:21
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public abstract class UserBaseEntity extends BaseEntity {
    
    /**
     * 创建人
     */
    @JsonIgnore
    private String createBy;
    
    /**
     * 修改人
     */
    @JsonIgnore
    private String updateBy;
    
}
