package uz.pdp.portfolio.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.portfolio.component.MailAuthenticator;
import uz.pdp.portfolio.component.MyMailAuthenticator;
import uz.pdp.portfolio.payload.MessageDTO;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static javax.mail.Message.RecipientType.TO;
import static uz.pdp.portfolio.utils.MessageType.HTML;

@Service
@RequiredArgsConstructor
public class MailService {

    private final Properties properties;

    private final MailAuthenticator mailAuthenticator;

    private final MyMailAuthenticator myMailAuthenticator;

    public boolean sendMessage(MessageDTO messageDTO) {
        MimeMessage message = new MimeMessage(Session.getDefaultInstance(properties, mailAuthenticator));
        try {
            message.setFrom(new InternetAddress(messageDTO.getEmail()));
            message.addRecipient(TO, new InternetAddress("bekzod.m070@gmail.com"));
            message.setSubject(messageDTO.getSubject());
            message.setContent("<h2><b>From: </b>" + messageDTO.getEmail() + "</h2>\n" +
                    "<h2><b>Name: </b>" + messageDTO.getName() + "</h2>\n" +
                    "<h3><b>Subject: </b>" + messageDTO.getSubject() + "</h3>\n" +
                    "<h3><b>Message: </b>" + messageDTO.getText() + "</h3>", HTML);
            new Thread(() -> {
                try {
                    Transport.send(message);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }).start();
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean sendMessageAsMe(MessageDTO messageDTO) {
        MimeMessage message = new MimeMessage(Session.getDefaultInstance(properties, myMailAuthenticator));
        try {
            message.setFrom(new InternetAddress(myMailAuthenticator.getUsername()));
            message.addRecipient(TO, new InternetAddress(messageDTO.getEmail()));
            message.setContent("<h3>Hi, My name is Bekzod</h3>\n" +
                    "<h2>You wrote me from this mail. How can I help you ? </h2>\n", HTML);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
}
