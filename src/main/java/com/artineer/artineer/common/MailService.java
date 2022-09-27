package com.artineer.artineer.common;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

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

    public String sendMail(List<String> users, String memberId) {
        List<String> userList = new ArrayList<>();

        for (String user : users) {
            userList.add(user);
        }

        int userSize = userList.size();

        SimpleMailMessage simpleMessage = new SimpleMailMessage();

        simpleMessage.setTo((String[]) userList.toArray(new String[userSize]));

        simpleMessage.setFrom("admin@artineer.com");

        simpleMessage.setSubject("[We Are Artineer!] " + memberId + " 님의 임시 비밀번호 입니다.");

        String temporaryPassword = temporaryPassword();
        simpleMessage.setText(temporaryPassword);

        javaMailSender.send(simpleMessage);

        return temporaryPassword;
    }
}