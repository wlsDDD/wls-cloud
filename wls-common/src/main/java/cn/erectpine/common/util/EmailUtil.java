package cn.erectpine.common.util;

import cn.erectpine.common.properties.WlsShareYml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 电子邮件工具类
 *
 * @Author wls
 * @Date 2021/3/15 16:27
 */
@Component
public class EmailUtil {
    
    @Autowired public WlsShareYml wlsYml;
    @Autowired public JavaMailSender javaMailSender;
    
    /**
     * 普通邮件发送
     *
     * @param title 邮件标题
     * @param text  邮件正文
     */
    public void sendSimpleMail(String title, String text) {
        sendSimpleMail(title, text, wlsYml.getEmailTo(), wlsYml.getAddressee());
    }
    
    /**
     * 发送简单的邮件
     * 普通邮件发送
     *
     * @param title   邮件标题
     * @param text    邮件正文
     * @param from    发件人
     * @param address 收件人-可以是多个
     */
    public void sendSimpleMail(String title, String text, String from, String... address) {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject(title);
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom(from);
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        message.setTo(address);
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        message.setText(text);
        // 发送邮件
        javaMailSender.send(message);
    }
    
}
