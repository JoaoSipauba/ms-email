package com.ms.msemail.services;

import com.ms.msemail.enums.StatusEmail;
import com.ms.msemail.models.EmailModel;
import com.ms.msemail.models.FileModel;
import com.ms.msemail.repositories.EmailRepository;
import com.ms.msemail.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;
    @Autowired
    FileRepository fileRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Transactional
    public EmailModel sendEmail(EmailModel emailModel, List<MultipartFile> files) {
        emailModel.setSendDateEmail(LocalDateTime.now());
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(emailModel.getEmailFrom());
            helper.setTo(emailModel.getEmailTo());
            helper.setSubject(emailModel.getSubject());
            helper.setText(emailModel.getText());

            if (files != null){
                for (MultipartFile file : files) {
                    helper.addAttachment(file.getOriginalFilename(), file);
                    FileModel fileModel = new FileModel();
                    fileModel.setFilename(file.getOriginalFilename());
                    fileModel.setFile(file.getBytes());
                    fileRepository.save(fileModel);
                }
            }


            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }
    }


}
