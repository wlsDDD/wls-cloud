package cn.erectpine.common.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collection;

/**
 * 分页对象
 *
 * @author wls
 * @date 2021/08/21 17:48:24
 */
@Data
@Accessors(chain = true)
public class PinePage implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 总计
     */
    private Long totalNum;
    /**
     * 第几页
     */
    private Integer pageNum;
    /**
     * 页面条数
     */
    private Integer pageSize;
    /**
     * 数据列表
     */
    private Collection<?> list;
    
}
