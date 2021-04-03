package cn.erectpine.system;

import cn.erectpine.common.enums.CodeMsgEnum;
import cn.erectpine.common.properties.WlsShareYml;
import cn.erectpine.common.util.Assert;
import cn.erectpine.common.web.exception.BusinessException;
import cn.erectpine.system.common.properties.WlsYml;
import cn.erectpine.system.project.entity.User;
import cn.erectpine.system.project.service.IUserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

@SpringBootTest
class WlsSystemApplicationTests {
    
    @Autowired WlsShareYml wlsShareYml;
    @Autowired WlsYml wlsYml;
    @Autowired JavaMailSender javaMailSender;
    @Autowired IUserService userService;
    
    @Test
    public void test02() {
        Assert.isZero(0, () -> new BusinessException(CodeMsgEnum.ARG_VERIFY_ERROR, "id不能为空"));
    }
    
    @Test
    public void test01() {
        userService.list(Wrappers.lambdaQuery(new User()).select());
    }
    
    /**
     * 普通邮件发送
     */
    @Test
    public void sendSimpleMail() {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("这是一封测试邮件");
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom("1626961806@qq.com");
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        // message.setTo("10*****16@qq.com","12****32*qq.com");
        message.setTo("1626961806@qq.com");
        // 设置邮件抄送人，可以有多个抄送人
//		message.setCc("12****32*qq.com");
        // 设置隐秘抄送人，可以有多个
//		message.setBcc("7******9@qq.com");
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        message.setText("这是测试邮件的正文");
        // 发送邮件
        javaMailSender.send(message);
    }
    
}
