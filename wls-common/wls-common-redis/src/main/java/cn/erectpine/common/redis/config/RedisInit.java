package cn.erectpine.common.redis.config;

import cn.erectpine.common.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 项目启动时初始化
 *
 * @Author wls
 * @Date 2021/9/17 9:43
 */
@Slf4j
@Component
public class RedisInit {
    
    @Autowired StringRedisTemplate redisTemplate;
    @Autowired RedissonClient redissonClient;
    
    /**
     * 初始化Redis
     */
    @PostConstruct
    public void initContext() {
        RedisUtil.redisTemplate = redisTemplate;
        log.info("[StringRedisTemplate -> 初始化] - [{}]", RedisUtil.redisTemplate);
        RedisUtil.redissonClient = redissonClient;
        log.info("[RedissonClient -> 初始化] - [{}]", RedisUtil.redissonClient);
    }
    
}