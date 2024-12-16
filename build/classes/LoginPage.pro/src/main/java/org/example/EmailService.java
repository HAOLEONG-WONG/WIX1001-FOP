package org.example;

import java.util.Properties;
import java.util.Random;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailService {

    // 生成6位验证码
    public static String generateCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;  // 生成6位数字验证码
        return String.valueOf(code);
    }

    // 发送验证码邮件
    public static boolean sendEmail(String recipientEmail, String code) {
        String host = "smtp.qq.com";
        String from = "3504763651@qq.com";
        String password = "sherzomunlczcjbb";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.qq.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("您的验证码");

            String body = "您的验证码是：" + code + "，有效期为5分钟。";
            message.setText(body);

            Transport.send(message);
            System.out.println("验证码邮件已发送到 " + recipientEmail);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 发送包含 PDF 附件的邮件
    public static boolean sendEmailWithAttachment(String recipientEmail, String subject, String body, String attachmentPath) {
        String host = "smtp.qq.com";
        String from = "3504763651@qq.com";
        String password = "sherzomunlczcjbb";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // 创建邮件消息
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);

            // 邮件正文
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(body);

            // 附件部分
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.setDataHandler(new DataHandler(new FileDataSource(attachmentPath)));
            attachmentPart.setFileName("student_info.pdf"); // 附件文件名

            // 创建 Multipart 组合内容
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            // 设置内容
            message.setContent(multipart);

            // 发送邮件
            Transport.send(message);
            System.out.println("带附件的邮件已发送到 " + recipientEmail);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
