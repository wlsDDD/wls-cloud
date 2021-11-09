package cn.erectpine.system;

import cn.erectpine.common.core.thread.PineThreadPoolExecutor;
import cn.erectpine.common.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

@Slf4j
@SpringBootTest
class WlsSystemApplicationTest {
    
    @Autowired PineThreadPoolExecutor pineThreadPoolExecutor;
    @Autowired RedisTemplate<String, String> redisTemplate;
    
    @Test
    public void test02() {
        Set<String> keys = redisTemplate.keys("wls-cloud:wls-system:dev:method-cache*");
    }
    
    @Test
    public void test03() {
        RedisUtil.redisTemplate.opsForValue().set("helloKey", "helloValue");
        Object helloKey = RedisUtil.redisTemplate.opsForValue().get("helloKey");
    }
    
    @Test
    public void test01() {
        System.out.println("pineThreadExecutor = " + pineThreadPoolExecutor);
        for (int i = 0; i < 100; i++) {
            pineThreadPoolExecutor.execute(() -> {
                log.info("有个线程执行开始");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("有个线程执行结束");
            });
        }
        
    }
    
}