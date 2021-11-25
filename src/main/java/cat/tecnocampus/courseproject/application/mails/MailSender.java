package cat.tecnocampus.courseproject.application.mails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class MailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @ResponseBody
    public void sendMessage() {
        String to = "lvong@edu.tecnocampus.cat";

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("noreplyopenfoodclub@gmail.com");
        message.setTo(to);
        message.setSubject("Summary of your order");
        message.setText("Please find the summary of your order.");

        this.javaMailSender.send(message);
    }

}
