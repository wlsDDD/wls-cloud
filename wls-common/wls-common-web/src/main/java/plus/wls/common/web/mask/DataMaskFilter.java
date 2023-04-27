package plus.wls.common.web.mask;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.serializer.ValueFilter;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 数据屏蔽过滤
 *
 * @author wls
 * @since 2023-04-26
 */
public class DataMaskFilter implements ValueFilter {
    
    /**
     * 所有要脱敏的字段名称
     */
    private final Set<String> fieldNames = Stream.of(
            "methodName",
            "methodName",
            "wls"
    ).collect(Collectors.toSet());
    
    @Override
    public Object process(Object object, String name, Object value) {
        if (fieldNames.contains(name)) {
            String val = value.toString();
            int length = val.length();
            int start = (int) Math.max(1, length * 0.2);
            start = Math.min(start, 5);
            int end = (int) Math.min(length, length * 0.8);
            return StrUtil.hide(val, start, end);
        }
        return value;
    }
    
}
