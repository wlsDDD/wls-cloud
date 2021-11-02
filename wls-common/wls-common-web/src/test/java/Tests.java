import cn.erectpine.common.core.enums.StatusEnum;
import cn.hutool.core.util.EnumUtil;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wls
 * @since 2021/11/2 9:13
 */
public class Tests {
    
    @Test
    public void test01() {
        Set<Integer> ordinal = EnumUtil.getFieldValues(StatusEnum.class, "ordinal").stream().map(o -> Integer.valueOf(o + "")).collect(Collectors.toSet());
    }
    
}
