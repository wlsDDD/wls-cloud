package plus.wls.common.web.pojo;

import lombok.Data;
import lombok.experimental.Delegate;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

/**
 * 如果请求体直接传递了json数组给后台，并希望对数组中的每一项都进行参数校验。
 * 此时，如果我们直接使用java.util.Collection下的list或者set来接收数据，参数校验并不会生效！我们可以使用自定义list集合来接收参数：
 * 包装 List类型，并声明 @Valid 注解
 *
 * @author wls
 * @since 2021/11/25 10:25:08
 */
@Data
public class ValidationList<E> implements List<E> {
    
    @Delegate
    @Valid
    public List<E> list = new LinkedList<>();
    
    @Override
    public String toString() {
        return list.toString();
    }
    
}
