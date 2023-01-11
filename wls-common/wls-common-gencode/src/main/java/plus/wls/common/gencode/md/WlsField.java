package plus.wls.common.gencode.md;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * bean属性
 *
 * @author wls
 * @since 2021/5/18 10:34
 */
@Data
@Accessors(chain = true)
public class WlsField {
    
    private String propertyName;
    private String propertyType;
    private String comment;
    private String required;
    
}
