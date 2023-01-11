package plus.wls.elasticsearch.framework.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wls
 * @since 2021/10/21 16:54
 */
@Data
@Accessors(chain = true)
public class User implements EsId {
    
    private Long id;
    private String name;
    private Integer age;
    private String phone;
    
}
