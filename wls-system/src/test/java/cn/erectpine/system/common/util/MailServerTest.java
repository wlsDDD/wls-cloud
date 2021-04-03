package cn.erectpine.system.common.util;

import cn.erectpine.common.util.MailServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MailServerTest {
    
    @Autowired MailServer mailServer;
    
    @Test
    void sendSimpleMail() {
        mailServer.sendSimpleMail("系统出现未知异常，请排查", "测试邮件功能");
    }
}