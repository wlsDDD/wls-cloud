package cn.erectpine.common.redis;

import cn.erectpine.common.core.constant.GlobalConstants;
import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.lang.Nullable;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 自定义Redis key 缓存序列化
 *
 * @author wls
 * @since 2021/11/04 11:57:29
 */
public class KeyStringRedisSerializer implements RedisSerializer<String> {
    
    
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    /**
     * 服务统一前缀缓存
     */
    private static final String CACHE_PREFIX = StrUtil.format("{}:{}:{}:", GlobalConstants.PROJECT_NAME, GlobalConstants.serviceName, GlobalConstants.active);
    
    /**
     * 序列化
     *
     * @see org.springframework.data.redis.serializer.RedisSerializer#serialize(java.lang.Object)
     */
    @Override
    public byte[] serialize(@Nullable String string) {
        return string == null ? null : (CACHE_PREFIX + string).getBytes(DEFAULT_CHARSET);
    }
    
    /**
     * 反序列化
     *
     * @see org.springframework.data.redis.serializer.RedisSerializer#deserialize(byte[])
     */
    @Override
    public String deserialize(@Nullable byte[] bytes) {
        return bytes == null ? null : new String(bytes, DEFAULT_CHARSET).replaceFirst(CACHE_PREFIX, "");
    }
    
}