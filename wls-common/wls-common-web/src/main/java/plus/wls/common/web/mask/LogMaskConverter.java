package plus.wls.common.web.mask;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.alibaba.fastjson.JSON;
import org.slf4j.helpers.MessageFormatter;

import java.util.stream.Stream;

public class LogMaskConverter extends MessageConverter {
    @Override
    public String convert(ILoggingEvent event) {
        try {
            Object[] objects = Stream.of(event.getArgumentArray()).map(obj -> {
                String msg;
                if (obj instanceof String) {
                    // String 类型直接打印
                    msg = obj.toString();
                } else {
                    // 其他类型 通过 fastjson Filter 功能脱敏后转成 json 字符串
                    msg = JSON.toJSONString(obj, new DataMaskFilter());
                }
                return msg;
            }).toArray();
            return MessageFormatter.arrayFormat(event.getMessage(), objects).getMessage();
        } catch (Exception e) {
            return event.getMessage();
        }
    }
    
}