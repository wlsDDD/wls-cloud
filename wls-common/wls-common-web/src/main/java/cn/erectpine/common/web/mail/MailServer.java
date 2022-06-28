package cn.erectpine.common.web.mail;

import cn.erectpine.common.core.constant.GlobalConstants;
import cn.erectpine.common.core.pojo.ApiLog;
import cn.erectpine.common.web.context.HttpContext;
import cn.erectpine.common.web.pojo.properties.WlsShareYml;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * 电子邮件工具类
 *
 * @author wls
 * @since 2021/3/15 16:27
 */
@Slf4j
@Component
public class MailServer {
    
    @Autowired public WlsShareYml wlsShareYml;
    @Autowired private JavaMailSender javaMailSender;
    
    /**
     * 发送普通邮件
     *
     * @param title   邮件标题
     * @param text    邮件正文
     * @param address 收件人-可以是多个
     */
    public void sendSimpleMail(String title, String text, String... address) {
        try {
            // 构建一个邮件对象
            SimpleMailMessage message = new SimpleMailMessage();
            // 设置邮件主题
            message.setSubject(title);
            // 设置邮件发送者，这个跟application.yml中设置的要一致
            message.setFrom(wlsShareYml.getEmailFrom());
            // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
            if (StrUtil.isAllEmpty(address)) {
                message.setTo(wlsShareYml.getEmailFrom());
            } else {
                message.setTo(address);
            }
            // 设置邮件发送日期
            message.setSentDate(new Date());
            // 设置邮件的正文
            message.setText(text);
            // 发送邮件
            javaMailSender.send(message);
            // 日志信息
            log.info("普通邮件发送成功。");
        } catch (MailException e) {
            log.error("发送普通邮件时异常！", e);
        }
    }
    
    /**
     * 发送html邮件
     *
     * @param title   邮件标题
     * @param text    邮件正文
     * @param address 收件人-可以是多个
     */
    public void sendHtmlMail(String title, String text, String... address) {
        //获取MimeMessage对象
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(wlsShareYml.getEmailFrom());
            //邮件接收人
            messageHelper.setTo(address);
            //邮件主题
            message.setSubject(title);
            //邮件内容，html格式
            messageHelper.setText(text, true);
            //发送
            javaMailSender.send(message);
            //日志信息
            log.info("html邮件发送成功。");
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常！", e);
        }
    }
    
    @Async
    public void sendApiLog() {
        ApiLog apiLog = HttpContext.getApiLog();
        // 排序
        JSONConfig jsonConfig = new JSONConfig()
                .setOrder(true)
                .setDateFormat(DatePattern.NORM_DATETIME_MS_PATTERN)
                .setIgnoreNullValue(false);
        JSON logJson = JSONUtil.parse(apiLog, jsonConfig);
        String title = StrUtil.format("{}服务-{}环境-发现异常！！",
                GlobalConstants.serviceName,
                GlobalConstants.active.name());
        sendSimpleMail(title, JSONUtil.toJsonPrettyStr(logJson));
    }
    
}
