package plus.wls.common.web.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import plus.wls.common.core.pojo.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * 扩展返回结果对象
 *
 * @author wls
 * @since 2021/9/4 19:44
 */
@Data
@Accessors(chain = true)
public class ResultExtra implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    /**
     * 分页列表
     */
    private Page<?> page;
    /**
     * 树列表
     */
    private Collection<?> tree;
    /**
     * 参数错误消息
     */
    private Map<String, String> paramErrors;
    
}
