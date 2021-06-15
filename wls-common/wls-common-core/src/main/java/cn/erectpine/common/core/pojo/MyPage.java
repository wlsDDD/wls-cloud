package cn.erectpine.common.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collection;

/**
 * 自定义分页对象
 *
 * @Author wls
 * @Date 2021/2/25 17:50
 */
@Data
@Accessors(chain = true)
public class MyPage implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 总计
     */
    private Long total;
    /**
     * 第几页
     */
    private Integer pageNo;
    /**
     * 页面条数
     */
    private Integer pageSize;
    /**
     * 数据列表
     */
    private Collection<?> list;
    
}
