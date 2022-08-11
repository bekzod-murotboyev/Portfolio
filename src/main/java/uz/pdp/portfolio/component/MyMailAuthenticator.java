package uz.pdp.portfolio.component;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

@Component
@Getter
public class MyMailAuthenticator extends Authenticator {

    public final String username = "bekzod.m070@gmail.com";

    private final String password = "fjeujrqqbboibmeq";

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
