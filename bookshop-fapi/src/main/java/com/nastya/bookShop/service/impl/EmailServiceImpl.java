package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.response.MessageResponse;
import com.nastya.bookShop.model.user.ConfirmationTokenDto;
import com.nastya.bookShop.model.user.UserDto;
import com.nastya.bookShop.service.api.ConfirmationTokenService;
import com.nastya.bookShop.service.api.EmailService;
import com.nastya.bookShop.service.api.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl implements EmailService {

    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;
    private final JavaMailSender mailSender;
    private final Environment env;

    @Autowired
    public EmailServiceImpl(UserService userService,
                            ConfirmationTokenService confirmationTokenService,
                            JavaMailSender mailSender, Environment env) {
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
        this.mailSender = mailSender;
        this.env = env;
    }

    @Override
    @Transactional
    public void sendVerificationEmail(UserDto user)
            throws MessagingException, UnsupportedEncodingException {
        ConfirmationTokenDto confirmationToken = saveToken(user);
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your Bookshop.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(env.getProperty("spring.mail.username"), "Nastya");
        helper.setTo(user.getEmail());
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFirstName() + " " + user.getLastName());
        String verifyURL = UrlConst.AngularAuthUrl + confirmationToken.getConfirmationToken();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);
        mailSender.send(message);
    }

    @Override
    @Transactional
    public void sendCreation(UserDto user, String randomCode)
            throws MessagingException, UnsupportedEncodingException {
        String subject = "You've been registered!";
        String content = "Your login: [[username]]<br>"
                + "Your password: [[password]]<br>"
                + "Thank you,<br>"
                + "Your Bookshop.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(env.getProperty("spring.mail.username"), "Nastya");
        helper.setTo(user.getEmail());
        helper.setSubject(subject);

        content = content.replace("[[username]]", user.getUsername());
        content = content.replace("[[password]]", randomCode);
        helper.setText(content, true);
        mailSender.send(message);
    }

    @Override
    public ResponseEntity<MessageResponse> verify(String verificationCode)
            throws UnsupportedEncodingException, MessagingException {
        ConfirmationTokenDto confirmationTokenDto = confirmationTokenService.findByToken(verificationCode);
        UserDto user = userService.findByUserName(confirmationTokenDto.getUsername());
        if (user == null || user.getIsEnabled()) {
            return (ResponseEntity) ResponseEntity.badRequest();
        } else {
            confirmationTokenDto.setConfirmationToken(null);
            user.setIsEnabled(true);
            userService.saveUser(user);
            confirmationTokenService.save(confirmationTokenDto);
            return ResponseEntity.ok(new MessageResponse("User verified successfully!"));
        }
    }

    @Override
    public ConfirmationTokenDto saveToken(UserDto user) {
        ConfirmationTokenDto confirmationToken = new ConfirmationTokenDto();
        String randomCode = RandomString.make(64);
        confirmationToken.setConfirmationToken(randomCode);
        confirmationToken.setUsername(user.getUsername());
        confirmationTokenService.save(confirmationToken);
        return confirmationToken;
    }

}
