package com.artineer.artineer.common;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    // 개행 문자
    private static final String newLine = System.getProperty("line.separator");

    public String temporaryPassword() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public String createContent(String memberId, String tempPassword) {
        String content = String.join(
                newLine,
                "<h1>[We Are Artineer!]</h1>",
                "<hr>",
                "<b>" + memberId + "</b>" + "님이 요청하신 임시 비밀번호 입니다.",
                "<h2>" + tempPassword + "</h2>"
                );

        return content;
    }

    // SimpleMailSender
    public String sendMail(List<String> users, String memberId) {
        List<String> userList = new ArrayList<>();

        for (String user : users) {
            userList.add(user);
        }

        int userSize = userList.size();

        SimpleMailMessage simpleMessage = new SimpleMailMessage();

        simpleMessage.setTo((String[]) userList.toArray(new String[userSize]));

        simpleMessage.setFrom("admin@artineer.net");

        simpleMessage.setSubject("[We Are Artineer!] " + memberId + " 님의 임시 비밀번호 입니다.");

        String temporaryPassword = temporaryPassword();
        simpleMessage.setText(temporaryPassword);

        javaMailSender.send(simpleMessage);

        return temporaryPassword;
    }

    // MimeMessage
    public String sendMessage(String memberId, String email) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        mimeMessage.setFrom("admin@artineer.net");
        mimeMessage.setRecipients(Message.RecipientType.TO, email);
        mimeMessage.setSubject("[We Are Artineer!] " + memberId + " 님의 임시 비밀번호 입니다.", "UTF-8");
        // 임시 비밀번호
        String tempPassword = temporaryPassword();
        mimeMessage.setContent(createContent(memberId, tempPassword), "text/html;charset=UTF-8");
        // 메일 전송
        javaMailSender.send(mimeMessage);

        return tempPassword;
    }
}