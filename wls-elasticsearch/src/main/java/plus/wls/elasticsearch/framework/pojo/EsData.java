package plus.wls.elasticsearch.framework.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * es数据
 *
 * @author wls
 * @since 2021/10/18 17:18:01
 */
@Data
@Accessors(chain = true)
public class EsData<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private T data;
    
}
