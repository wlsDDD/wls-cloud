package plus.wls.common.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.lang.Nullable;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static plus.wls.common.redis.enums.CachePrefixEnum.CACHE_PREFIX;

/**
 * 自定义Redis key 缓存序列化
 *
 * @author wls
 * @since 2021/11/04 11:57:29
 */
public class KeyStringRedisSerializer implements RedisSerializer<String> {
    
    
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    
    /**
     * 序列化
     *
     * @see org.springframework.data.redis.serializer.RedisSerializer#serialize(java.lang.Object)
     */
    @Override
    public byte[] serialize(@Nullable String string) {
        return string == null ? null : (CACHE_PREFIX.getPrefix() + string).getBytes(DEFAULT_CHARSET);
    }
    
    /**
     * 反序列化
     *
     * @see org.springframework.data.redis.serializer.RedisSerializer#deserialize(byte[])
     */
    @Override
    public String deserialize(@Nullable byte[] bytes) {
        return bytes == null ? null : new String(bytes, DEFAULT_CHARSET).replaceFirst(CACHE_PREFIX.getPrefix(), "");
    }
    
}