package cn.erectpine.common.web.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 扩展返回结果对象
 *
 * @Author wls
 * @Date 2021/9/4 19:44
 */
@Data
@Accessors(chain = true)
public class ExpandResult implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 树
     */
    private List<?> tree;
    
}
