package plus.wls.common.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collection;

/**
 * 分页对象
 *
 * @author wls
 * @since 2021/08/21 17:48:24
 */
@Data
@Accessors(chain = true)
public class Page<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 数据列表
     */
    private Collection<T> list;
    /**
     * 第几页
     */
    private Integer pageNum;
    /**
     * 每页条数
     */
    private Integer pageSize;
    /**
     * 总计
     */
    private Long totalNum;
    
}
