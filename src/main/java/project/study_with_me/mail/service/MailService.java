package project.study_with_me.mail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendRequestEmailNotice(String email, String studyTitle, String name){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(email); // 메일 수신자
            mimeMessageHelper.setSubject(studyTitle + " 스터디에 참여 신청이 왔습니다."); // 메일 제목
            mimeMessageHelper.setText(setRequestContext(studyTitle, name, "request"), true); // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);

            log.info("Succeeded to send Email");
        } catch (Exception e) {
            log.info("Failed to send Email");
            throw new RuntimeException(e);
        }
    }

    @Async
    public void sendAcceptEmailNotice(String email, Boolean check, String studyTitle, String name){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(email); // 메일 수신자
            mimeMessageHelper.setSubject(studyTitle + " 스터디 참여 신청 결과"); // 메일 제목
            if (check.equals(false)) {
                mimeMessageHelper.setText(setAcceptContext(
                        "신청하신 " + studyTitle + " 스터디 요청이 거절되었습니다.",
                        "accept", name), true); // 메일 본문 내용, HTML 여부
            } else {
                mimeMessageHelper.setText(setAcceptContext(
                        "신청하신 " + studyTitle + "스터디 요청이 수락되었습니다.",
                        "accept", name), true); // 메일 본문 내용, HTML 여부
            }
            javaMailSender.send(mimeMessage);

            log.info("Succeeded to send Email");
        } catch (Exception e) {
            log.info("Failed to send Email");
            throw new RuntimeException(e);
        }
    }

    //thymeleaf를 통한 html 적용
    public String setRequestContext(String studyTitle, String name, String template) {
        Context context = new Context();
        context.setVariable("study", studyTitle);
        context.setVariable("name", name);
        return templateEngine.process(template, context);
    }

    //thymeleaf를 통한 html 적용
    public String setAcceptContext(String check, String template, String name) {
        Context context = new Context();
        context.setVariable("check", check);
        context.setVariable("name", name);
        return templateEngine.process(template, context);
    }
}
