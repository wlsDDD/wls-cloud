package cn.erectpine.system;

import cn.erectpine.common.core.thread.PineThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class WlsSystemApplicationTest {
    
    @Autowired PineThreadPoolExecutor pineThreadPoolExecutor;
    
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