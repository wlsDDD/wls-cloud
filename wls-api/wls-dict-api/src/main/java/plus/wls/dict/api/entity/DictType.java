package plus.wls.dict.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import plus.wls.common.web.pojo.BaseEntity;

/**
 * <p>
 * 字典类型
 * </p>
 *
 * @author wls
 * @since 2021-03-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true, fluent = true)
@TableName("sys_dict_type")
public class DictType extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 字典主键
     */
    @TableId(value = "dict_id", type = IdType.AUTO)
    private Long dictId;
    /**
     * 字典名称
     */
    private String dictName;
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    
}
